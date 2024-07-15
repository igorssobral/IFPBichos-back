package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import java.time.LocalDateTime;



public class DonationDTO {

	private LocalDateTime date;
	private float donationValue;
	private Boolean isDirected;
	
	
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
		return isDirected;
	}
	public void setIsDirected(Boolean isDirected) {
		this.isDirected = isDirected;
	}
	
	
	
}
