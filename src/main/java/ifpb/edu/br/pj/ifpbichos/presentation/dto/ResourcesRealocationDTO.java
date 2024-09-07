package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResourcesRealocationDTO {

    private LocalDateTime date;
    private BigDecimal value;
    private Long campaignId;
    private String typeRealocation;

}

