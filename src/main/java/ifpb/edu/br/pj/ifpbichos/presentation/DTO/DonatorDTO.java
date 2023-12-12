package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.enums.DonatorType;

public class DonatorDTO extends UserDTO{
	
	private String registration;
	
	private DonatorType donatorType;
	
	public DonatorDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DonatorDTO(String nome, String phoneNumber, String email, String registration , DonatorType donatorType) {
		super(nome, phoneNumber, email);
		this.donatorType = donatorType;
		this.registration = registration;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public DonatorType getDonatorType() {
		return donatorType;
	}

	public void setDonatorType(DonatorType donatorType) {
		this.donatorType = donatorType;
	}
	
	
}
