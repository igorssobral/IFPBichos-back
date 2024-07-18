package ifpb.edu.br.pj.ifpbichos.business.service.converter;

import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import ifpb.edu.br.pj.ifpbichos.model.enums.Animal;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.CampaignDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CampaignConverterService {
    public Campaign dtoToCampaign(CampaignDTO dto){

        if (dto != null) {


            Campaign campaign = new Campaign(dto.getId(), dto.getStart(), dto.getEnd(), dto.getTitle(), dto.getDescription(), true,
                            null, dto.getCollectionGoal(), dto.getCollectionPercentage(), dto.getBalance(), dto.getUndirectedBalance(), Animal.valueOf(dto.getAnimal()));
                    return campaign;


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
            if (entity.getCollectionGoal().compareTo(BigDecimal.ZERO) != 0) {
                // Calcular o progresso (balance / collectionGoal) * 100
                BigDecimal progress = entity.getBalance().divide(entity.getCollectionGoal(), 4, RoundingMode.HALF_UP)
                        .multiply(new BigDecimal("100"))
                        .setScale(2, RoundingMode.HALF_UP);

                int progressInt = progress.intValue();
                CampaignDTO dto = new CampaignDTO(entity.getId(), entity.getStart(), entity.getEnd(), entity.getTitle(), entity.getDescription(), entity.isCampaingStatus(),
                        encodedImage, entity.getCollectionGoal(), progressInt, entity.getBalance(), entity.getUndirectedBalance(), entity.getAnimal() != null ? entity.getAnimal().toString() : "");
                return dto;
            }


        }
        return null;
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

