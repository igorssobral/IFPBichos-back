package ifpb.edu.br.pj.ifpbichos.business.service.converter;

import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import ifpb.edu.br.pj.ifpbichos.model.entity.CampaignActionResource;
import ifpb.edu.br.pj.ifpbichos.model.repository.CampaignRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.CampaignActionResourceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignActionResourceConverterService {

    @Autowired
    private CampaignRepository campaignRepository;

    public CampaignActionResourceDTO convertToDTO(CampaignActionResource entity) {
        if (entity == null) {
            return null;
        }

        CampaignActionResourceDTO dto = new CampaignActionResourceDTO();
        dto.setId(entity.getId());
        dto.setCampaignId(entity.getCampaign().getId());
        dto.setJustification(entity.getJustification());
        dto.setAction(entity.getAction());
        dto.setCost(entity.getCost());
        dto.setCompletionDate(entity.getCompletionDate());
        dto.setReceipt(entity.getReceipt());
        dto.setWithdrawalFromUndirectedBalance(entity.getWithdrawalFromUndirectedBalance());

        return dto;
    }

    public CampaignActionResource convertToEntity(CampaignActionResourceDTO dto) {
        if (dto == null) {
            return null;
        }

        CampaignActionResource entity = new CampaignActionResource();
        entity.setId(dto.getId());

        Campaign campaign = campaignRepository.findById(dto.getCampaignId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid campaign ID"));

        entity.setCampaign(campaign);
        entity.setJustification(dto.getJustification());
        entity.setAction(dto.getAction());
        entity.setCost(dto.getCost());
        entity.setCompletionDate(dto.getCompletionDate());
        entity.setReceipt(dto.getReceipt());
        entity.setWithdrawalFromUndirectedBalance(dto.getWithdrawalFromUndirectedBalance());

        return entity;
    }
}
