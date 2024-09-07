package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import ifpb.edu.br.pj.ifpbichos.business.service.CampaignService;
import ifpb.edu.br.pj.ifpbichos.business.service.converter.CampaignConverterService;
import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import ifpb.edu.br.pj.ifpbichos.model.repository.CampaignRepository;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.CampaignDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/campaign")
@Validated
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CampaignConverterService converterService;

    @GetMapping
    public ResponseEntity<List<CampaignDTO>> getAll() {
        List<Campaign> entityList = campaignService.findAll();
        List<CampaignDTO> dtoList = converterService.CampaignsToDtos(entityList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/getAllFinished")
    public ResponseEntity<List<CampaignDTO>> getAllFinished() {
        List<Campaign> entityList = campaignService.findAllFinished();
        List<CampaignDTO> dtoList = converterService.CampaignsToDtos(entityList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/finishedBalance")
    public ResponseEntity<List<CampaignDTO>> getAllFinishedHighZero() {
        List<Campaign> entityList = campaignService.findAllFinishedBalance();
        List<CampaignDTO> dtoList = converterService.CampaignsToDtos(entityList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Campaign entity = campaignService.findById(id);
            CampaignDTO dto = converterService.campaignToDto(entity);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campaign not found: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<CampaignDTO> save(@Valid @RequestBody CampaignDTO dto) {
        try {
            Campaign entity = converterService.dtoToCampaign(dto);
            entity = campaignService.save(entity);
            CampaignDTO responseDto = converterService.campaignToDto(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CampaignDTO dto) {
        try {
            Campaign entity = campaignService.findById(id);
            if (entity == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campaign not found");
            }
            entity.setTitle(dto.getTitle());
            entity.setEnd(dto.getEnd());
            entity.setDescription(dto.getDescription());
            entity.setAnimal(dto.getAnimal());
            entity.setImage(null);

            campaignService.update(entity);
            CampaignDTO responseDto = converterService.campaignToDto(entity);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating campaign: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            campaignService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/total-balance")
    public ResponseEntity<BigDecimal> getTotalBalance() {
        BigDecimal totalBalance = campaignService.getTotalBalance();
        return ResponseEntity.ok(totalBalance);
    }
}
