package ifpb.edu.br.pj.ifpbichos.business.service.converter;

import ifpb.edu.br.pj.ifpbichos.business.service.UndirectedBalanceService;
import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import ifpb.edu.br.pj.ifpbichos.model.entity.ResourcesRealocation;
import ifpb.edu.br.pj.ifpbichos.model.entity.UndirectedBalance;
import ifpb.edu.br.pj.ifpbichos.model.repository.ResourcesRealocationRepository;
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

    @Autowired
    public ResourcesRealocationService(ResourcesRealocationRepository resourcesRealocationRepository, UndirectedBalanceService undirectedBalanceService) {
        this.resourcesRealocationRepository = resourcesRealocationRepository;
        this.undirectedBalanceService = undirectedBalanceService;
    }

    public List<ResourcesRealocation> findAll() {
        return resourcesRealocationRepository.findAll();
    }

    public ResourcesRealocation findById(Long id) throws ObjectNotFoundException {
        return resourcesRealocationRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Realocação de recurso", "id", id));
    }

    public ResourcesRealocation save(ResourcesRealocation resourcesRealocation) throws InvalidObjectException {
        validateResourcesRealocation(resourcesRealocation);
        return resourcesRealocationRepository.save(resourcesRealocation);
    }

    public ResourcesRealocation update(Long id, ResourcesRealocation resourcesRealocation) throws ObjectNotFoundException, InvalidObjectException {
        if (!resourcesRealocationRepository.existsById(id)) {
            throw new ObjectNotFoundException("Realocação de recurso", "id", id);
        }
        validateResourcesRealocation(resourcesRealocation);
        resourcesRealocation.setId(id);
        return resourcesRealocationRepository.save(resourcesRealocation);
    }

    public void deleteById(Long id) throws ObjectNotFoundException {
        if (!resourcesRealocationRepository.existsById(id)) {
            throw new ObjectNotFoundException("Realocação de recurso", "id", id);
        }
        resourcesRealocationRepository.deleteById(id);
    }

    private void validateResourcesRealocation(ResourcesRealocation resourcesRealocation) throws InvalidObjectException {
        if (resourcesRealocation.getCampaign() == null) {
            throw new InvalidObjectException("O campo campanha não pode ser nulo");
        }
        if (resourcesRealocation.getDate() == null) {
            throw new InvalidObjectException("O campo data não pode ser nulo");
        }
        if (resourcesRealocation.getValue() == null || resourcesRealocation.getValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidObjectException("O valor da realocação deve ser maior que zero e não pode ser nulo.");
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
