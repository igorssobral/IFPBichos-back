package ifpb.edu.br.pj.ifpbichos.presentation.controller;
import ifpb.edu.br.pj.ifpbichos.model.enums.Animal;
import ifpb.edu.br.pj.ifpbichos.model.repository.CampaignRepository;
import org.springframework.http.HttpStatus;

import ifpb.edu.br.pj.ifpbichos.business.service.CampaignService;
import ifpb.edu.br.pj.ifpbichos.business.service.converter.CampaignConverterService;
import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.CampaignDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CampaignConverterService converterService;
    @Autowired
    private CampaignRepository campaignRepository;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Campaign> entityList = campaignService.findAll();
        List<CampaignDTO> dtoList = converterService.CampaignsToDtos(entityList);
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        try {
            Campaign entity = campaignService.findById(id);
            CampaignDTO dto = converterService.campaignToDto(entity);

            return ResponseEntity.ok().body(dto);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CampaignDTO dto) {

        try {

            Campaign entity = converterService.dtoToCampaign(dto);

            entity = campaignService.save(entity);
            dto = converterService.campaignToDto(entity);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CampaignDTO dto) {
        try {
            Optional<Campaign> entityOptional = campaignRepository.findById(id);
            if (entityOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campaign not found");
            }
            Campaign entity = entityOptional.get();
            entity.setTitle(dto.getTitle());
            entity.setEnd(dto.getEnd());
            entity.setDescription(dto.getDescription());
            entity.setAnimal(dto.getAnimal());
            entity.setImage(null);

            campaignService.update(entity);
            dto = converterService.campaignToDto(entity);
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        try {
            campaignService.deleteById(id);

            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
