package ifpb.edu.br.pj.ifpbichos.business.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import ifpb.edu.br.pj.ifpbichos.model.repository.CampaignRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.MissingFieldException;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectAlreadyExistsException;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectNotFoundException;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CampaignService {
	
	@Autowired
	private CampaignRepository campaignRepository;
	
	public boolean existsById(Integer id) {
		return campaignRepository.existsById(id);
	}
	
	public boolean existsByTitle(String title) {
		return campaignRepository.existsByTitle(title);
	}
	
	
	public List<Campaign> findAll() {
		return campaignRepository.findAll();
	}
	
	public Optional<Campaign> findByName(String title) throws Exception {
		if (title == null || title.isBlank()) {
			throw new MissingFieldException("nome");
		}
		
		if (!existsByTitle(title)) {
			throw new ObjectNotFoundException("local", "nome", title);
		}
		return campaignRepository.findByTitle(title);
	}
	
	public Campaign findById(Integer id) throws Exception {
		if (id == null) {
			throw new MissingFieldException("id");
		}
		
		if (!existsById(id)) {
			throw new ObjectNotFoundException("local", "id", id);
		}
		return campaignRepository.getReferenceById(id);
	}
	
	public Campaign save(Campaign campaign) throws Exception {
	
		if (existsByTitle(campaign.getTitle())) {
			throw new ObjectAlreadyExistsException("Já existe um local com nome " + campaign.getTitle());
		}
		
		return campaignRepository.save(campaign);
	}
	
	public Campaign update(Campaign campaign) throws Exception {
		
		if (campaign.getId() == null) {
			throw new MissingFieldException("id", "update");
		} else if (!existsById(campaign.getId())) {
			throw new ObjectNotFoundException("local", "id", campaign.getId());
		} 
		
		if (existsByTitle(campaign.getTitle())) {
			Campaign placeSaved = findByName(campaign.getTitle()).get();
			if (placeSaved.getId() != campaign.getId()) {
				throw new ObjectAlreadyExistsException("Já existe um local com o título " + campaign.getTitle());
			}
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

	public void deleteById(Integer id) throws Exception {
		if (id == null) {
			throw new MissingFieldException("id", "delete");
		} else if (!existsById(id)) {
			throw new ObjectNotFoundException("campanha", "id", id);
		}

		campaignRepository.deleteById(id);
	}

	public void saveCampaignWithImage(MultipartFile imageFile, Campaign campaign) throws IOException {
		campaign.setImage(imageFile.getBytes());
		campaignRepository.save(campaign);
	}

}
