package ifpb.edu.br.pj.ifpbichos.business.service;

import ifpb.edu.br.pj.ifpbichos.business.service.converter.CampaignActionResourceConverterService;
import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import ifpb.edu.br.pj.ifpbichos.model.entity.CampaignActionResource;
import ifpb.edu.br.pj.ifpbichos.model.repository.CampaignActionResourceRepository;
import ifpb.edu.br.pj.ifpbichos.model.repository.CampaignRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.CampaignActionResourceDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CampaignActionResourceService {

    @Autowired
    private CampaignActionResourceRepository repository;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignActionResourceConverterService campaignActionResourceConverterService;

    @Transactional
    public CampaignActionResource save(CampaignActionResourceDTO actionResource) throws Exception {
        if (actionResource == null) {
            throw new IllegalArgumentException("CampaignActionResource must not be null");
        }

        try {
            CampaignActionResource campaignActionResource = new CampaignActionResource();
            Campaign campaign = campaignService.findById(actionResource.getCampaignId());

            if(campaign == null){
                throw new ObjectNotFoundException("Campanha não encontrada!");
            }
            campaignActionResource.setCampaign(campaign);
            campaignActionResource.setAction(campaign.getTitle());
            campaignActionResource.setCost(campaign.getBalance());
            campaignActionResource.setCompletionDate(LocalDateTime.now());
            campaignActionResource.setWithdrawalFromUndirectedBalance(campaign.getUndirectedBalance());
            campaignActionResource.setJustification(campaign.getDescription());
            campaignActionResource.setReceipt(actionResource.getReceipt());
            campaign.setBalance(BigDecimal.ZERO);
            campaign.setUndirectedBalance(BigDecimal.ZERO);

            CampaignActionResource campaignActionResource1 = repository.save(campaignActionResource);
            campaign.setCampaignActionResource(campaignActionResource1);
            campaignRepository.save(campaign);

            return campaignActionResource1;
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to save CampaignActionResource", e);
        }
    }

    @Transactional
    public CampaignActionResource update(Long id, CampaignActionResource updatedResource) {
        if (updatedResource == null) {
            throw new IllegalArgumentException("Updated CampaignActionResource must not be null");
        }

        try {
            return repository.findById(id)
                    .map(existingResource -> {
                        existingResource.setAction(updatedResource.getAction());
                        existingResource.setCost(updatedResource.getCost());
                        existingResource.setCompletionDate(updatedResource.getCompletionDate());
                        existingResource.setReceipt(updatedResource.getReceipt());
                        return repository.save(existingResource);
                    })
                    .orElseThrow(() -> new ObjectNotFoundException("CampaignActionResource", "id", id));
        } catch (DataAccessException | ObjectNotFoundException e) {
            throw new RuntimeException("Failed to update CampaignActionResource", e);
        }
    }


    public void delete(Long id) {
        try {
            if (!repository.existsById(id)) {
                throw new ObjectNotFoundException("CampaignActionResource", "id", id);
            }
            repository.deleteById(id);
        } catch (DataAccessException | ObjectNotFoundException e) {
            throw new RuntimeException("Failed to delete CampaignActionResource", e);
        }
    }

    public CampaignActionResource findOne(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new ObjectNotFoundException("CampaignActionResource", "id", id));
        } catch (DataAccessException | ObjectNotFoundException e) {
            throw new RuntimeException("Failed to find CampaignActionResource", e);
        }
    }

    public List<CampaignActionResourceDTO> findAll() {
        try {
            List<CampaignActionResource> campaignActionResourceList = repository.findAll();
            List<CampaignActionResourceDTO> campaignActionResourceDTOList = campaignActionResourceList.stream().map(CampaignActionResourceDTO::new).toList();
            return campaignActionResourceDTOList;
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to retrieve CampaignActionResources", e);
        }
    }
}

