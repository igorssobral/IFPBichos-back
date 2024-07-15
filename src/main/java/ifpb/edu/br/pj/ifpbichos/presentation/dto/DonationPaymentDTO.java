package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.enums.DonationPaymentStatus;

public class DonationPaymentDTO {
	
	private String transactionId;
	private DonationPaymentStatus status;
	
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
	
}
