package ifpb.edu.br.pj.ifpbichos.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import ifpb.edu.br.pj.ifpbichos.model.enums.Animal;
import jakarta.persistence.*;


@Table(name = "CAMPAING")
@Entity
public class Campaign implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "START_DATE", nullable = false)
	private LocalDateTime start;
	@Column(name = "END_DATE", nullable = false)
	private LocalDateTime end;
	@Column(name = "CAMPAIGN_TITLE", nullable = false, unique = true)
	private String title;
	@Column(name = "CAMPAIGN_DESCRIPTION")
	private String description;
	//true = ativa. false = encerrada

	@OneToMany(mappedBy = "campaign")
	private List<Donation> donations;

	@Column(name = "CAMPAIGN_ANIMAL")
	private Animal animal;


	@Column(name = "CAMPAIGN_STATUS", nullable = false)
	private boolean campaingStatus;

	@Lob
	@Column(name = "CAMPAIGN_PET_IMAGE")
	private byte[] image;
	@Column(name = "COLLECTION_GOAL", nullable = false)
	private BigDecimal collectionGoal;
	@Column(name = "COLLECTION_PERCENTAGE", nullable = false)
	private float collectionPercentage;
	@Column(name = "BALANCE", nullable = false)
	private BigDecimal balance;
	@Column(name = "UNDIRECTED_BALANCE", nullable = false)
	private BigDecimal undirectedBalance;
	
	public Campaign() {
		
	}

	public Campaign(LocalDateTime start, LocalDateTime end, String title, String description, byte[] image,float collectionPercentage,  BigDecimal balance,
	BigDecimal undirectedBalance,Animal animal) {
		this.start = start;
		this.end = end;
		this.title = title;
		this.description = description;
		this.image = image;
		this.collectionPercentage = collectionPercentage;
		this.balance = BigDecimal.valueOf(0);
		this.undirectedBalance = BigDecimal.valueOf(0);
		this.animal=animal;
	}

	public Campaign(Long id, LocalDateTime start, LocalDateTime end, String title, String description, boolean campaingStatus, byte[] image, BigDecimal collectionGoal, float collectionPercentage, BigDecimal balance, BigDecimal undirectedBalance, Animal animal) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCampaingStatus() {
		return campaingStatus;
	}

	public void setCampaingStatus(boolean campaingStatus) {
		this.campaingStatus = campaingStatus;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public BigDecimal getCollectionGoal() {
		return collectionGoal;
	}

	public void setCollectionGoal(BigDecimal collectionGoal) {
		this.collectionGoal = collectionGoal;
	}

	public float getCollectionPercentage() {
		return collectionPercentage;
	}

	public void setCollectionPercentage(float collectionPercentage) {
		this.collectionPercentage = collectionPercentage;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getUndirectedBalance() {
		return undirectedBalance;
	}

	public Animal getAnimal(){
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public void setUndirectedBalance(BigDecimal undirectedBalance) {
		this.undirectedBalance = undirectedBalance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public int hashCode() {
		return Objects.hash(balance, campaingStatus, collectionGoal, collectionPercentage, description, end, id, image,
				start, title, undirectedBalance);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Campaign campaign)) return false;
        return isCampaingStatus() == campaign.isCampaingStatus() && Float.compare(getCollectionPercentage(), campaign.getCollectionPercentage()) == 0 && Objects.equals(getId(), campaign.getId()) && Objects.equals(getStart(), campaign.getStart()) && Objects.equals(getEnd(), campaign.getEnd()) && Objects.equals(getTitle(), campaign.getTitle()) && Objects.equals(getDescription(), campaign.getDescription()) && Objects.equals(donations, campaign.donations) && getAnimal() == campaign.getAnimal() && Objects.deepEquals(getImage(), campaign.getImage()) && Objects.equals(getCollectionGoal(), campaign.getCollectionGoal()) && Objects.equals(getBalance(), campaign.getBalance()) && Objects.equals(getUndirectedBalance(), campaign.getUndirectedBalance());
	}

	@Override
	public String toString() {
		return "Campaign [id=" + id + ", start=" + start + ", end=" + end + ", title=" + title + ", description="
				+ description + ", campaingStatus=" + campaingStatus + ", image=" + image + ", collectionGoal="
				+ collectionGoal + ", collectionPercentage=" + collectionPercentage + ", balance=" + balance
				+ ", undirectedBalance=" + undirectedBalance + "]";
	}
	
	
	
}
