package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CampaignNotificationDTO {

    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String title;
    private String description;

    public CampaignNotificationDTO(Campaign campaign) {
        this.id = campaign.getId();
        this.start = campaign.getStart();
        this.end = campaign.getEnd();
        this.title = campaign.getTitle();
        this.description = campaign.getDescription();
    }
}
