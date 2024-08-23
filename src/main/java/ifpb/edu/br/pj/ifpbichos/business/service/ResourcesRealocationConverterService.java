package ifpb.edu.br.pj.ifpbichos.business.service;

import ifpb.edu.br.pj.ifpbichos.model.entity.ResourcesRealocation;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.ResourcesRealocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
