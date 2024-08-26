package ifpb.edu.br.pj.ifpbichos.business.service;

import ifpb.edu.br.pj.ifpbichos.model.entity.CampaignActionResource;
import ifpb.edu.br.pj.ifpbichos.model.repository.CampaignActionResourceRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignActionResourceService {

    @Autowired
    private CampaignActionResourceRepository repository;

    public CampaignActionResource save(CampaignActionResource actionResource) {
        if (actionResource == null) {
            throw new IllegalArgumentException("CampaignActionResource must not be null");
        }

        try {
            return repository.save(actionResource);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to save CampaignActionResource", e);
        }
    }

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

    public List<CampaignActionResource> findAll() {
        try {
            return repository.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to retrieve CampaignActionResources", e);
        }
    }
}

