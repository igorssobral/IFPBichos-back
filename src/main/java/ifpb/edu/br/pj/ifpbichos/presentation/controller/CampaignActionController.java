package ifpb.edu.br.pj.ifpbichos.presentation.controller;

import ifpb.edu.br.pj.ifpbichos.business.service.CampaignActionResourceService;
import ifpb.edu.br.pj.ifpbichos.presentation.dto.CampaignActionResourceDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/action-campaign")
@Validated
public class CampaignActionController {

    @Autowired
    private CampaignActionResourceService campaignActionResourceService;

    @PostMapping
    public ResponseEntity<String> saveWithdrawal(@Valid @RequestBody CampaignActionResourceDTO campaignActionResourceDTO) {
        try {
            campaignActionResourceService.save(campaignActionResourceDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Retirada salva com sucesso!");
        } catch (Exception e) {
            // Log the exception (ex: logger.error("Error saving withdrawal", e);)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a retirada: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CampaignActionResourceDTO>> getAll() {
        List<CampaignActionResourceDTO> campaignActionResourceDTOList = campaignActionResourceService.findAll();
        return ResponseEntity.ok(campaignActionResourceDTOList);
    }
}
