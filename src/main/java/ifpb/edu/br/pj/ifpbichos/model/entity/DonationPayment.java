package ifpb.edu.br.pj.ifpbichos.model.entity;

import java.io.Serializable;
import java.util.Objects;

import ifpb.edu.br.pj.ifpbichos.model.enums.DonationPaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DonationPayment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "TRANSACTION_ID", nullable = false)
	private String transactionId;
	@Column(name = "PAYMENT_STATUS", nullable = false)
	private DonationPaymentStatus status;
	
	public DonationPayment() {
		
	}
	
	public DonationPayment(String transactionId, DonationPaymentStatus status) {
		this.transactionId = transactionId;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public DonationPaymentStatus getStatus() {
		return status;
	}
	public void setStatus(DonationPaymentStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DonationPayment [id=" + id + ", transactionId=" + transactionId + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, status, transactionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonationPayment other = (DonationPayment) obj;
		return Objects.equals(id, other.id) && status == other.status
				&& Objects.equals(transactionId, other.transactionId);
	}
	
	
}
