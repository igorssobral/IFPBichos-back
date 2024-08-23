package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ResourcesRealocationDTO {

    private LocalDateTime date;
    private BigDecimal value;
    private Long campaignId;


    public ResourcesRealocationDTO() {}

    public ResourcesRealocationDTO(LocalDateTime date, BigDecimal value, Long campaignId) {
        this.date = date;
        this.value = value;
        this.campaignId = campaignId;
    }
}

