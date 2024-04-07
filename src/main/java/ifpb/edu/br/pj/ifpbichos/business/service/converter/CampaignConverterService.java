package ifpb.edu.br.pj.ifpbichos.business.service.converter;

import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.CampaignDTO;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.ArrayList;

@Service
public class CampaignConverterService {
    public Campaign dtoToCampaign(CampaignDTO dto){
        if (dto != null) {
//            if (dto.getImage() != null && !dto.getImage().isEmpty()) {

//                try {
//                    byte[] imageBytes = Base64.getDecoder().decode(dto.getImage());

//                    System.out.println(imageBytes);

                    Campaign campaign = new Campaign(dto.getId(), dto.getStart(), dto.getEnd(), dto.getTitle(), dto.getDescription(), dto.isCampaingStatus(),
                            null, dto.getCollectionGoal(), dto.getCollectionPercentage(), dto.getBalance(), dto.getUndirectedBalance());
                    System.out.println(campaign.getTitle());
                    return campaign;
//                } catch (IllegalArgumentException e) {
//                    throw new IllegalArgumentException("Erro ao decodificar a imagem Base64: " + e.getMessage());
//                }
//            }

        }
        throw new IllegalArgumentException("Não foi possível converter pois o objeto é nulo");
    }

    public CampaignDTO campaignToDto(Campaign entity) {
        if (entity != null) {
            byte[] imageBytes = entity.getImage();
            String encodedImage = "";
            if (imageBytes != null) {
                encodedImage = Base64.getEncoder().encodeToString(imageBytes);

            }
            CampaignDTO dto = new CampaignDTO(entity.getId(), entity.getStart(), entity.getEnd(), entity.getTitle(), entity.getDescription(), entity.isCampaingStatus(),
                    encodedImage, entity.getCollectionGoal(), entity.getCollectionPercentage(), entity.getBalance(), entity.getUndirectedBalance());
            return dto;
        }

        throw new IllegalArgumentException("Não foi possível converter pois o objeto é nulo");
    }

    public List<Campaign> dtosToCampaigns(List<CampaignDTO> dtoList) {

        if (dtoList != null) {
            List<Campaign> entityList = new ArrayList<>();

            Campaign entity = null;

            if (dtoList != null && !dtoList.isEmpty()) {
                for (CampaignDTO dto : dtoList) {
                    entity = dtoToCampaign(dto);
                    entityList.add(entity);
                }
            }

            return entityList;
        }

        throw new IllegalArgumentException("Não foi possível converter pois o objeto é nulo");
    }

    public List<CampaignDTO> CampaignsToDtos(List<Campaign> entityList) {
        if (entityList != null) {
            List<CampaignDTO> dtoList = new ArrayList<>();

            CampaignDTO dto = null;

            if (!entityList.isEmpty()) {
                for (Campaign campaign: entityList) {
                    dto = campaignToDto(campaign);
                    dtoList.add(dto);
                }
            }

            return dtoList;
        }

        throw new IllegalArgumentException("Não foi possível converter pois o objeto é nulo");
    }


}

