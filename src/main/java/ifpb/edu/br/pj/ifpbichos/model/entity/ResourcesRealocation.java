package ifpb.edu.br.pj.ifpbichos.model.entity;

import ifpb.edu.br.pj.ifpbichos.model.enums.ResourceRealocationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ResourcesRealocation implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "CAMPAIGN_ID", nullable = false)
    private Campaign campaign;

    private ResourceRealocationType typeRealocation;

    private LocalDateTime date;

    private BigDecimal value;

}
