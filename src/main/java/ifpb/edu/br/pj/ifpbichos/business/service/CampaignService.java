package ifpb.edu.br.pj.ifpbichos.business.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ifpb.edu.br.pj.ifpbichos.handler.WebSocketSessionManager;
import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import ifpb.edu.br.pj.ifpbichos.model.repository.CampaignRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.CampaignNotificationDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CampaignService {
	
	@Autowired
	private CampaignRepository campaignRepository;


	@Autowired
	private WebSocketSessionManager webSocketSessionManager;


	public boolean existsById(Long id) {
		return campaignRepository.existsById(id);
	}
	
	public boolean existsByTitle(String title)
	{
		return campaignRepository.existsByTitle(title);
	}
	
	
	public List<Campaign> findAll() {
		return campaignRepository.findAllByOrderByStartDesc();
	}
	
	public Optional<Campaign> findByName(String title) throws Exception {
		if (title == null || title.isBlank()) {
			throw new MissingFieldException("nome");
		}
		
		if (!existsByTitle(title)) {
			throw new ObjectNotFoundException("Campanha", "nome", title);
		}
		return campaignRepository.findByTitle(title);
	}
	
	public Campaign findById(Long id) throws Exception {
		if (id == null) {
			throw new MissingFieldException("id");
		}
		
		if (!existsById(id)) {
			throw new ObjectNotFoundException("Campanha", "id", id);
		}
		return campaignRepository.getReferenceById(id);
	}

	public Campaign save(Campaign campaign) throws Exception {

		if (existsByTitle(campaign.getTitle())) {
			throw new ObjectAlreadyExistsException("Já existe uma campanha com nome " + campaign.getTitle());
		}
		if (campaign.getStart().isAfter(campaign.getEnd())) {
			throw new InvalidDateRangeException("Não pode criar essa campanha Data de início deve ser anterior à data de término");
		}

		if (campaign.getStart().isBefore(LocalDate.now().atStartOfDay())) {
			throw new InvalidDateRangeException("Não pode criar essa campanha Data de início deve ser no futuro");
		}

		if (campaign.getCollectionGoal().compareTo(BigDecimal.ZERO) < 0) {
			throw new InvalidCollectionGoalException("O orçamento da campanha deve ser um valor positivo");
		}

		if (campaign.getBalance().compareTo(BigDecimal.ZERO) < 0) {
			throw new InvalidCollectionPercentageException("O balanço da campanha deve ser um valor positivo");
		}

		Campaign campaignSaved = campaignRepository.save(campaign);

		sendNotification(campaign);

		return campaignSaved;
	}

	public Campaign update(Campaign campaign) throws Exception {
		System.out.println(campaign);
		if (campaign.getId() == null) {
			throw new MissingFieldException("id", "update");
		} else if (!existsById(campaign.getId())) {
			throw new ObjectNotFoundException("Campanha", "id", campaign.getId());
		} 
		
		if (existsByTitle(campaign.getTitle())) {
			Campaign campaignSaved = findByName(campaign.getTitle()).orElseThrow(
					() -> new NullPointerException("Campaign by title not found"));

			if (!Objects.equals(campaignSaved.getTitle(), campaign.getTitle())) {
				throw new ObjectAlreadyExistsException("Já existe uma campanha com o título " + campaign.getTitle());
			}
		}

		if (campaign.getStart().isAfter(campaign.getEnd())) {
			throw new InvalidDateRangeException("Não pode editar essa campanha Data de início deve ser anterior à data de término");
		}

		if (campaign.getEnd().isBefore(LocalDate.now().atStartOfDay())) {
			throw new InvalidDateRangeException("Não pode editar essa campanha Data de início deve ser no futuro");
		}

		if (campaign.getBalance().compareTo(BigDecimal.ZERO) < 0) {
			throw new InvalidCollectionGoalException("O orçamento da campanha deve ser um valor positivo");
		}

		if (campaign.getUndirectedBalance().compareTo(BigDecimal.ZERO) < 0) {
			throw new InvalidCollectionPercentageException("A porcentagem da campanha deve ser um valor positivo");
		}

		if(!campaign.isCampaingStatus()){
			throw new CampaignNotEditableException("A campanha ja foi encerrada e nao pode ser atualizada");
		}

		return campaignRepository.save(campaign);
	}
	
	public void delete(Campaign campaign) throws Exception {
		if (campaign.getId() == null) {
			throw new MissingFieldException("id", "delete");
		} else if (!existsById(campaign.getId())) {
			throw new ObjectNotFoundException("local", "id", campaign.getId());
		}
		
		campaignRepository.delete(campaign);
	}

	public void deleteById(Long id) throws Exception {
		if (id == null) {
			throw new MissingFieldException("id", "delete");
		} else if (!existsById(id)) {
			throw new ObjectNotFoundException("campanha", "id", id);
		}

		campaignRepository.deleteById(id);
	}

	public void saveCampaignWithImage(MultipartFile imageFile, Campaign campaign) throws Exception {
		if (!Arrays.asList("image/jpeg", "image/png").contains(imageFile.getContentType())) {
			throw new InvalidImageTypeException("O tipo da imagem deve ser JPEG ou PNG");
		}
		campaign.setImage(imageFile.getBytes());
		campaignRepository.save(campaign);
	}

	private void sendNotification(Campaign campaign) {
		CampaignNotificationDTO notification = new CampaignNotificationDTO(campaign);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		String notificationJson;
		try {
			notificationJson = objectMapper.writeValueAsString(notification);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao converter a notificação para JSON", e);
		}

		for (WebSocketSession session : webSocketSessionManager.getSessions()) {
			if (session.isOpen()) {
				try {
					session.sendMessage(new TextMessage(notificationJson));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public BigDecimal getTotalBalance() {
		return campaignRepository.findTotalBalance();
	}

}
