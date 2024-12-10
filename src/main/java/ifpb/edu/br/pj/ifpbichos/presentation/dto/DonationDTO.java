package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ifpb.edu.br.pj.ifpbichos.model.entity.Donation;
import ifpb.edu.br.pj.ifpbichos.model.enums.DonationPaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class DonationDTO {

	private Integer id;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String title;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String description;

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
		this.title = donation.getTitle() != null ? donation.getTitle() : "";
		this.description = donation.getDescription() != null ? donation.getDescription() : "";
		this.preferenceId = donation.getPreferenceId();
		this.paymentId = donation.getPaymentId();
		this.donator = new DonatorDTO(donation.getDonator());
		this.campaign = new CampaignDTO(donation.getCampaign());
		this.date = donation.getDate();
		this.donationValue = donation.getDonationValue();
		this.status = donation.getStatus();
		this.directed = donation.getDirected();
	}

}
