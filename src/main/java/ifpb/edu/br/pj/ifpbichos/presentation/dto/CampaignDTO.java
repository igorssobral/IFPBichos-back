package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import java.time.LocalDateTime;

public class CampaignDTO {
    
	private Integer id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String title;
    private String description;
    private boolean campaingStatus;
    private String image;
    private float collectionGoal;
    private float collectionPercentage;
    private float balance;
    private float undirectedBalance;

    public CampaignDTO() {}

    public Integer getId() {
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

    public float getCollectionGoal() {
        return collectionGoal;
    }

    public float getCollectionPercentage() {
        return collectionPercentage;
    }

    public float getBalance() {
        return balance;
    }

    public float getUndirectedBalance() {
        return undirectedBalance;
    }

    public void setId(Integer id) {
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

    public void setCollectionGoal(float collectionGoal) {
        this.collectionGoal = collectionGoal;
    }

    public void setCollectionPercentage(float collectionPercentage) {
        this.collectionPercentage = collectionPercentage;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setUndirectedBalance(float undirectedBalance) {
        this.undirectedBalance = undirectedBalance;
    }

    public CampaignDTO(Integer id, LocalDateTime start, LocalDateTime end, String title, String description, boolean campaingStatus, String image, float collectionGoal, float collectionPercentage, float balance, float undirectedBalance) {
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
    }

    public CampaignDTO(LocalDateTime start, LocalDateTime end, String title, String description, boolean campaingStatus, String image, float collectionGoal, float collectionPercentage, float balance, float undirectedBalance) {
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
    }
}
