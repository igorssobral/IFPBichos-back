package ifpb.edu.br.pj.ifpbichos.model.entity;

import ifpb.edu.br.pj.ifpbichos.model.enums.ComissionMemberRole;
import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

import java.util.Objects;

@Entity
@Table(name = "COMISSION_MEMBER", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_CPF"})})
@Data
public class ComissionMember extends User {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "MEMBER_ROLE")
	private ComissionMemberRole role;

	public ComissionMember(String name,String cpf, String phoneNumber, String email, String login, String password, UserRoles userRole,ComissionMemberRole role) {
		super(name,cpf, phoneNumber, email, login, password, userRole);
		this.role = role;
	}

	public ComissionMember() {}

	public ComissionMemberRole getRole() {
		return role;
	}
	public void setRole(ComissionMemberRole role) {
		this.role = role;
	}

	@Override
	public String getUsername() {
		return super.getLogin();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}


