package ifpb.edu.br.pj.ifpbichos.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

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
	private double donationValue;
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
	public double getDonationValue() {
		return donationValue;
	}
	public void setDonationValue(float donationValue) {
		this.donationValue = donationValue;
	}

	public Boolean getDirected() {
		return directed;
	}

	public void setDirected(Boolean directed) {
		this.directed = directed;
	}

	@Override
	public String toString() {
		return "Donation [id=" + id + ", date=" + date + ", donationValue=" + donationValue + ", directed=" + directed
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, directed, donationValue, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donation other = (Donation) obj;
		return Objects.equals(date, other.date) && Objects.equals(directed, other.directed)
				&& Double.doubleToLongBits(donationValue) == Double.doubleToLongBits(other.donationValue)
				&& Objects.equals(id, other.id);
	}
	
	
}
