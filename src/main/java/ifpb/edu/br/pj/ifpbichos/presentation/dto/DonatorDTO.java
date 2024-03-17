package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.enums.DonatorType;
import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;

public class DonatorDTO extends UserDTO {
	
	private String registration;
	
	private DonatorType donatorType;
	
	public DonatorDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DonatorDTO(String name, String phoneNumber, String email,String login, String password, UserRoles userRole,String registration, DonatorType donatorType) {
		super(name, phoneNumber, email, login, password, userRole);
		this.registration = registration;
		this.donatorType = donatorType;
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
