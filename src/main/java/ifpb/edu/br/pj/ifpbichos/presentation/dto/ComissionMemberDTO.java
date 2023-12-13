package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.enums.ComissionMemberRole;

public class ComissionMemberDTO extends UserDTO {
	
	private String CPF;
	private ComissionMemberRole role;
	
	public ComissionMemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ComissionMemberDTO(String name, String phoneNumber, String email,String login, String password, String CPF, ComissionMemberRole role) {
		super(name, phoneNumber, email, login, password);
		this.CPF = CPF;
		this.role = role;
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
