package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.entity.User;
import ifpb.edu.br.pj.ifpbichos.model.enums.DonatorType;
import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;
import lombok.Data;

@Data
public class DonatorDTO extends UserDTO {
	
	private String registration;


	private DonatorType donatorType;
	

	public DonatorDTO(String name,String cpf, String phoneNumber, String email,String login, String password, UserRoles userRole,String registration, DonatorType donatorType) {
		super(name,cpf, phoneNumber, email, login, password, userRole);
		this.registration = registration;
		this.donatorType = donatorType;
	}


	public DonatorDTO(User donator) {
	}
}
