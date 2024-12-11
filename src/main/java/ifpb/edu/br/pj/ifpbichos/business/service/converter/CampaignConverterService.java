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

        if (dto == null) {
            throw new NullPointerException("Não foi possível converter pois o objeto DTO é nulo");
        }

        return new Campaign(
                dto.getId(),
                dto.getStart(),
                dto.getEnd(),
                dto.getTitle(),
                dto.getDescription(),
                true,
                null,
                dto.getCollectionGoal(),
                dto.getCollectionPercentage(),
                dto.getBalance(),
                dto.getUndirectedBalance(),
                dto.getAnimal()
        );

    }

    public CampaignDTO campaignToDto(Campaign entity) {
        if (entity == null) {
            throw  new NullPointerException("Não foi possível converter pois o objeto campanha é nulo");
        }

        String encodedImage = encodeImage(entity.getImage());
        int progressInt = calculateProgress(entity.getBalance(), entity.getCollectionGoal());

        return new CampaignDTO(
                entity.getId(),
                entity.getStart(),
                entity.getEnd(),
                entity.getTitle(),
                entity.getDescription(),
                entity.isCampaingStatus(),
                encodedImage,
                entity.getCollectionGoal(),
                progressInt,
                entity.getBalance(),
                entity.getUndirectedBalance(),
                entity.getAnimal()
        );

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


    private String encodeImage(byte[] imageBytes) {
        return imageBytes != null ? Base64.getEncoder().encodeToString(imageBytes) : "";
    }

    private int calculateProgress(BigDecimal balance, BigDecimal collectionGoal) {
        if (collectionGoal.compareTo(BigDecimal.ZERO) == 0) {
            return 0;
        }
        BigDecimal progress = balance.divide(collectionGoal, 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal("100"))
                .setScale(2, RoundingMode.HALF_UP);
        return progress.intValue();
    }

}

