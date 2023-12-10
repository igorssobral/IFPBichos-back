package ifpb.edu.br.pj.ifpbichos.model.entity;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "CAMPAING")
public class Campaign implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDateTime start;
	private LocalDateTime end;
	private String title;
	private String description;
	private boolean campaingStatus;
	private File image;
	private float collectionGoal;
	private float collectionPercentage;
	private float balance;
	private float undirectedBalance;
	
	public Campaign() {
		
	}
	
	public Campaign(LocalDateTime start, LocalDateTime end, String title, String description, File image,float collectionPercentage,  float balance,
	float undirectedBalance) {
		this.start = start;
		this.end = end;
		this.title = title;
		this.description = description;
		this.image = image;
		this.collectionPercentage = collectionPercentage;
		this.balance = 0;
		this.undirectedBalance = 0;
	}

	public Campaign(Integer id, LocalDateTime start, LocalDateTime end, String title, String description, boolean campaingStatus, File image, float collectionGoal, float collectionPercentage, float balance, float undirectedBalance) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public float getCollectionGoal() {
		return collectionGoal;
	}

	public void setCollectionGoal(float collectionGoal) {
		this.collectionGoal = collectionGoal;
	}

	public float getCollectionPercentage() {
		return collectionPercentage;
	}

	public void setCollectionPercentage(float collectionPercentage) {
		this.collectionPercentage = collectionPercentage;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public float getUndirectedBalance() {
		return undirectedBalance;
	}

	public void setUndirectedBalance(float undirectedBalance) {
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campaign other = (Campaign) obj;
		return Float.floatToIntBits(balance) == Float.floatToIntBits(other.balance)
				&& campaingStatus == other.campaingStatus
				&& Float.floatToIntBits(collectionGoal) == Float.floatToIntBits(other.collectionGoal)
				&& Float.floatToIntBits(collectionPercentage) == Float.floatToIntBits(other.collectionPercentage)
				&& Objects.equals(description, other.description) && Objects.equals(end, other.end)
				&& Objects.equals(id, other.id) && Objects.equals(image, other.image)
				&& Objects.equals(start, other.start) && Objects.equals(title, other.title)
				&& Float.floatToIntBits(undirectedBalance) == Float.floatToIntBits(other.undirectedBalance);
	}

	@Override
	public String toString() {
		return "Campaign [id=" + id + ", start=" + start + ", end=" + end + ", title=" + title + ", description="
				+ description + ", campaingStatus=" + campaingStatus + ", image=" + image + ", collectionGoal="
				+ collectionGoal + ", collectionPercentage=" + collectionPercentage + ", balance=" + balance
				+ ", undirectedBalance=" + undirectedBalance + "]";
	}
	
	
	
}
