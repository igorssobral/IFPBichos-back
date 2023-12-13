package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.enums.ComissionMemberRole;

public class ComissionMemberDTO extends UserDTO {
	
	private String CPF;
	private ComissionMemberRole role;
	
	public ComissionMemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ComissionMemberDTO(String name, String phoneNumber, String email, String cPF, ComissionMemberRole role) {
		super(name, phoneNumber, email);
		CPF = cPF;
		this.role = role;
	}

	public ComissionMemberDTO(String name, String phoneNumber, String email, String CPF) {
		super(name, phoneNumber, email);
		CPF = CPF;

	}

	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public ComissionMemberRole getRole() {
		return role;
	}
	public void setRole(ComissionMemberRole role) {
		this.role = role;
	}
	
	
}
