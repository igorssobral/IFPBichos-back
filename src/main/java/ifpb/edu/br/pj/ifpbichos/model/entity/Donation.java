package ifpb.edu.br.pj.ifpbichos.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Donation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "DONATION_DATE", nullable = false)
	private LocalDateTime date;
	@Column(name = "DONATION_VALUE", nullable = false)
	private float donationValue;
	@Column(name = "DIRECTED", nullable = false)
	private Boolean directed;
	
	public Donation() {
		
	}
	
	public Donation(LocalDateTime date, float donationValue, Boolean isDirected) {
		this.date = date;
		this.donationValue = donationValue;
		this.directed = isDirected;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public float getDonationValue() {
		return donationValue;
	}
	public void setDonationValue(float donationValue) {
		this.donationValue = donationValue;
	}
	public Boolean getIsDirected() {
		return directed;
	}
	public void setIsDirected(Boolean directed) {
		this.directed = directed;
	}
	
	
}
