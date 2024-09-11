package ifpb.edu.br.pj.ifpbichos.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "CAMPAIGN_ACTION_RESOURCE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignActionResource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "CAMPAIGN_ID", nullable = false)
    private Campaign campaign;

    @Column(nullable = false, name = "JUSTIFICATION")
    private String justification;

    @Column(nullable = false, name = "ACTION")
    private String action;

    @Column(nullable = false, name = "COST")
    private BigDecimal cost;

    @Column(nullable = false, name = "DATE")
    private LocalDateTime completionDate;

    @Lob
    @Column(name = "RECEIPT_IMAGE")
    private byte[] receipt;

    @Column(name = "UNDIRECTED_BALANCE_USED")
    private BigDecimal withdrawalFromUndirectedBalance;


}
