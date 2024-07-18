package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.enums.Animal;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private BigDecimal balance;
    private BigDecimal undirectedBalance;
    private String animal;

    public CampaignDTO() {}

    public Long getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCampaingStatus() {
        return campaingStatus;
    }

    public String getImage() {
        return image;
    }

    public BigDecimal getCollectionGoal() {
        return collectionGoal;
    }

    public float getCollectionPercentage() {
        return collectionPercentage;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getUndirectedBalance() {
        return undirectedBalance;
    }

    public String getAnimal() {
        return animal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCampaingStatus(boolean campaingStatus) {
        this.campaingStatus = campaingStatus;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCollectionGoal(BigDecimal collectionGoal) {
        this.collectionGoal = collectionGoal;
    }

    public void setCollectionPercentage(float collectionPercentage) {
        this.collectionPercentage = collectionPercentage;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setUndirectedBalance(BigDecimal undirectedBalance) {
        this.undirectedBalance = undirectedBalance;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public CampaignDTO(Long id, LocalDateTime start, LocalDateTime end, String title, String description, boolean campaingStatus, String image, BigDecimal collectionGoal, float collectionPercentage, BigDecimal balance, BigDecimal undirectedBalance, String animal) {
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

    public CampaignDTO(LocalDateTime start, LocalDateTime end, String title, String description, boolean campaingStatus, String image, BigDecimal collectionGoal, float collectionPercentage, BigDecimal balance, BigDecimal undirectedBalance,String animal) {
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
}
