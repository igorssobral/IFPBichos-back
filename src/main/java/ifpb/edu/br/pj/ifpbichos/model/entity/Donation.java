package ifpb.edu.br.pj.ifpbichos.model.entity;

import ifpb.edu.br.pj.ifpbichos.model.enums.DonationPaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Donation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "PREFERENCE_ID", nullable = false)
	private String preferenceId;

	@Column(name = "PAYMENT_ID", nullable = true)
	private String paymentId;

	@Column(name = "PAYMENT_TYPE", nullable = true)
	private String paymentType;

	@ManyToOne
	@JoinColumn(name = "DONATOR_ID")
	private User donator;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Campaign campaign;

	@Column(name = "DONATION_DATE", nullable = false)
	private LocalDateTime date;

	@Column(name = "DONATION_VALUE", nullable = false)
	private BigDecimal donationValue;

	@Column(name = "PAYMENT_STATUS", nullable = false)
	private DonationPaymentStatus status;

	@Column(name = "DIRECTED", nullable = false)
	private Boolean directed;
	
	public Donation() {}
	
	public Donation(LocalDateTime date, BigDecimal donationValue, Boolean isDirected) {
		this.date = date;
		this.donationValue = donationValue;
		this.directed = isDirected;
	}

	
}
