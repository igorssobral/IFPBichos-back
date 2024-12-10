package ifpb.edu.br.pj.ifpbichos.business.service;

import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import ifpb.edu.br.pj.ifpbichos.model.entity.ResourcesRealocation;
import ifpb.edu.br.pj.ifpbichos.model.entity.UndirectedBalance;
import ifpb.edu.br.pj.ifpbichos.model.enums.ResourceRealocationType;
import ifpb.edu.br.pj.ifpbichos.model.repository.CampaignRepository;
import ifpb.edu.br.pj.ifpbichos.model.repository.ResourcesRealocationRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.ResourcesRealocationDTO;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResourcesRealocationService {

    private final ResourcesRealocationRepository resourcesRealocationRepository;
    private final UndirectedBalanceService undirectedBalanceService;
    private final CampaignService campaignService;
    private final CampaignRepository campaignRepository;

    @Autowired
    public ResourcesRealocationService(ResourcesRealocationRepository resourcesRealocationRepository, UndirectedBalanceService undirectedBalanceService, CampaignService campaignService, CampaignRepository campaignRepository) {
        this.resourcesRealocationRepository = resourcesRealocationRepository;
        this.undirectedBalanceService = undirectedBalanceService;
        this.campaignService = campaignService;
        this.campaignRepository = campaignRepository;
    }

    public List<ResourcesRealocation> findAll() {
        return resourcesRealocationRepository.findAll();
    }

    public ResourcesRealocation findById(Long id) throws ObjectNotFoundException {
        return resourcesRealocationRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Realocação de recurso", "id", id));
    }

    @Transactional
    public ResourcesRealocation save(ResourcesRealocationDTO resourcesRealocationDTO) throws Exception {
        validateResourcesRealocation(resourcesRealocationDTO);

        Campaign campaign = campaignService.findById(resourcesRealocationDTO.getCampaignId());
        UndirectedBalance undirectedBalance = undirectedBalanceService.getCurrentBalanceEntity();

        processRealocation(resourcesRealocationDTO, campaign, undirectedBalance);

        ResourcesRealocation resourcesRealocation = createResourcesRealocation(resourcesRealocationDTO, campaign);

        campaignRepository.save(campaign);
        return resourcesRealocationRepository.save(resourcesRealocation);
    }

    private void processRealocation(ResourcesRealocationDTO dto, Campaign campaign, UndirectedBalance undirectedBalance) throws Exception {
        if (dto.getTypeRealocation().equals("withdrawal")) {
            campaign.setUndirectedBalance(dto.getValue());
            undirectedBalance.setBalance(undirectedBalance.getBalance().subtract(dto.getValue()));
        }
    }

    private ResourcesRealocation createResourcesRealocation(ResourcesRealocationDTO dto, Campaign campaign) {
        ResourcesRealocation resourcesRealocation = new ResourcesRealocation();

        ResourceRealocationType realocationType = dto.getTypeRealocation().equals("withdrawal")
                ? ResourceRealocationType.EXTRA_BALANCE_WITHDRAWAL
                : ResourceRealocationType.EXTRA_BALANCE_ENTRY;

        resourcesRealocation.setTypeRealocation(realocationType);
        resourcesRealocation.setCampaign(campaign);
        resourcesRealocation.setValue(dto.getValue());
        resourcesRealocation.setDate(LocalDateTime.now());

        campaign.setResourcesRealocation(resourcesRealocation);

        return resourcesRealocation;
    }

    public ResourcesRealocation update(Long id, ResourcesRealocationDTO resourcesRealocationDTO) throws Exception {
        if (!resourcesRealocationRepository.existsById(id)) {
            throw new ObjectNotFoundException("Realocação de recurso", "id", id);
        }

        validateResourcesRealocation(resourcesRealocationDTO);
        ResourcesRealocation resourcesRealocation = resourcesRealocationRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Realocação de recurso", "id", id));

        resourcesRealocation.setId(id);
        return resourcesRealocationRepository.save(resourcesRealocation);
    }

    public void deleteById(Long id) throws ObjectNotFoundException {
        if (!resourcesRealocationRepository.existsById(id)) {
            throw new ObjectNotFoundException("Realocação de recurso", "id", id);
        }
        resourcesRealocationRepository.deleteById(id);
    }

    private void validateResourcesRealocation(ResourcesRealocationDTO resourcesRealocation) throws Exception {

        if (resourcesRealocation.getCampaignId() == null) {
            throw new InvalidObjectException("O campo campanha não pode ser nulo");
        }

        if (resourcesRealocation.getValue() == null || resourcesRealocation.getValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidObjectException("O valor da realocação deve ser maior que zero e não pode ser nulo.");
        }

        if (resourcesRealocation.getValue().compareTo(undirectedBalanceService.getCurrentBalance()) > 0) {
            throw new InvalidObjectException("O valor da realocação é insuficiente.");
        }
    }

    @Transactional
    public void createResourcesRealocationIfNeeded(Campaign campaign) {
        if (!campaign.isCampaingStatus() && campaign.getBalance().compareTo(campaign.getCollectionGoal()) > 0) {
            BigDecimal excessAmount = campaign.getBalance().subtract(campaign.getCollectionGoal());

            ResourcesRealocation resourcesRealocation = new ResourcesRealocation();
            resourcesRealocation.setCampaign(campaign);
            resourcesRealocation.setDate(LocalDateTime.now());
            resourcesRealocation.setValue(excessAmount);
            resourcesRealocationRepository.save(resourcesRealocation);

            // Atualizando o saldo não direcionado
            try {
                UndirectedBalance undirectedBalance = undirectedBalanceService.getCurrentBalanceEntity();
                undirectedBalanceService.update(undirectedBalance, excessAmount);
            } catch (Exception e) {
                throw new RuntimeException("Falha ao atualizar o saldo não direcionado", e);
            }
        }
    }
}
