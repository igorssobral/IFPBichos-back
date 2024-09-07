package ifpb.edu.br.pj.ifpbichos.model.entity;

import ifpb.edu.br.pj.ifpbichos.model.enums.Animal;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ToString(exclude = "donations")
@Data
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

	@OneToOne
	private ResourcesRealocation resourcesRealocation;


	
	public Campaign() {}

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

	public BigDecimal getBalance() {
		return this.balance.add(this.undirectedBalance);
	}

	@PrePersist
	@PreUpdate
	public void verifyStatus() {
		updateStatus();
	}

	public void updateStatus() {
		this.campaingStatus = !this.end.isBefore(LocalDateTime.now());
	}

	
}
