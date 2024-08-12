package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.entity.Donation;
import ifpb.edu.br.pj.ifpbichos.model.enums.DonationPaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class DonationDTO {

	private Integer id;

	private String preferenceId;

	private String paymentId;

	private UserDTO donator;

	private CampaignDTO campaign;

	private LocalDateTime date;

	private BigDecimal donationValue;

	private DonationPaymentStatus status;

	private Boolean directed;


	public DonationDTO(Donation donation) {
		this.id = donation.getId();
		this.preferenceId = donation.getPreferenceId();
		this.paymentId = donation.getPaymentId();
		this.donator = new DonatorDTO(donation.getDonator());
		this.campaign = new CampaignDTO(donation.getCampaign());
		this.date = donation.getDate();
		this.donationValue = donation.getDonationValue();
		this.status = donation.getStatus();
		this.directed = donation.getDirected();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPreferenceId() {
		return preferenceId;
	}

	public void setPreferenceId(String preferenceId) {
		this.preferenceId = preferenceId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public UserDTO getDonator() {
		return donator;
	}

	public void setDonator(UserDTO donator) {
		this.donator = donator;
	}

	public CampaignDTO getCampaign() {
		return campaign;
	}

	public void setCampaign(CampaignDTO campaign) {
		this.campaign = campaign;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public BigDecimal getDonationValue() {
		return donationValue;
	}

	public void setDonationValue(BigDecimal donationValue) {
		this.donationValue = donationValue;
	}

	public DonationPaymentStatus getStatus() {
		return status;
	}

	public void setStatus(DonationPaymentStatus status) {
		this.status = status;
	}

	public Boolean getDirected() {
		return directed;
	}

	public void setDirected(Boolean directed) {
		this.directed = directed;
	}
}
