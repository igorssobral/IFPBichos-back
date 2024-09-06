package ifpb.edu.br.pj.ifpbichos.business.service.converter;

import ifpb.edu.br.pj.ifpbichos.business.service.CampaignService;
import ifpb.edu.br.pj.ifpbichos.model.entity.ResourcesRealocation;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.ResourcesRealocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourcesRealocationConverterService {

    @Autowired
    private CampaignService service;

    public ResourcesRealocationDTO toDto(ResourcesRealocation entity) {
        return new ResourcesRealocationDTO(
                entity.getDate(),
                entity.getValue(),
                entity.getCampaign().getId() 
        );
    }

    public ResourcesRealocation toEntity(ResourcesRealocationDTO dto) throws Exception {
        ResourcesRealocation entity = new ResourcesRealocation();
        entity.setDate(dto.getDate());
        entity.setValue(dto.getValue());
        // Buscar a campanha pelo ID
        entity.setCampaign(service.findById(dto.getCampaignId()));
        return entity;
    }

    public List<ResourcesRealocationDTO> ResourcesRealocationToDtos(List<ResourcesRealocation> entityList) {
        if (entityList == null) {
            throw new IllegalArgumentException("Lista de realocações de recursos não pode ser nula");
        }

        List<ResourcesRealocationDTO> dtoList = new ArrayList<>();

        for (ResourcesRealocation resourcesRealocation : entityList) {
            if (resourcesRealocation == null) {
                throw new IllegalArgumentException("Objeto ResourcesRealocation não pode ser nulo");
            }

            ResourcesRealocationDTO dto = toDto(resourcesRealocation);
            dtoList.add(dto); // Assumindo que toDto nunca retorna null
        }

        return dtoList;
        }
    }

