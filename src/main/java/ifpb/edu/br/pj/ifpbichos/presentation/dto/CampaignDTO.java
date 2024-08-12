package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.entity.Campaign;
import ifpb.edu.br.pj.ifpbichos.model.enums.Animal;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class CampaignDTO {
    
	private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String title;
    private String description;
    private boolean campaingStatus;
    private String image;
    private BigDecimal collectionGoal;
    private float collectionPercentage;
    private BigDecimal balance = BigDecimal.valueOf(0);
    private BigDecimal undirectedBalance = BigDecimal.valueOf(0);
    private Animal animal;

    public CampaignDTO() {}


    public CampaignDTO(Long id, LocalDateTime start, LocalDateTime end, String title, String description, boolean campaingStatus, String image, BigDecimal collectionGoal, float collectionPercentage, BigDecimal balance, BigDecimal undirectedBalance, Animal animal) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.title = title;
        this.description = description;
        this.campaingStatus = campaingStatus;
        this.image = image;
        this.collectionGoal = collectionGoal;
        this.collectionPercentage = collectionPercentage;
        this.balance = balance;
        this.undirectedBalance = undirectedBalance;
        this.animal=animal;
    }


    public CampaignDTO(Campaign campaign) {
    }
}
