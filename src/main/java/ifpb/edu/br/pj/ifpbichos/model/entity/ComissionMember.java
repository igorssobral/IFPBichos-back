package ifpb.edu.br.pj.ifpbichos.model.entity;

import java.util.Objects;

import ifpb.edu.br.pj.ifpbichos.model.enums.ComissionMemberRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "COMISSION_MEMBER", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_CPF"})})
public class ComissionMember extends User{
	
	@Column(name = "USER_CPF")
	private String CPF;
	@Column(name = "MEMBER_ROLE")
	private ComissionMemberRole role;
	
	public ComissionMember() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ComissionMember(String name, String phoneNumber, String email, String CPF,ComissionMemberRole role) {
		super(name, phoneNumber, email);
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(CPF, role);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComissionMember other = (ComissionMember) obj;
		return Objects.equals(CPF, other.CPF) && role == other.role;
	}
}
