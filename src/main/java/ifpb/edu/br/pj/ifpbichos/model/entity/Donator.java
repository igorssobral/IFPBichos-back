package ifpb.edu.br.pj.ifpbichos.model.entity;

import ifpb.edu.br.pj.ifpbichos.model.enums.DonatorType;
import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "DONATOR", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_CPF"})})
@Data
public class Donator extends User {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "DONATOR_TYPE")
	private DonatorType donatorType;

	public Donator() {}

	public Donator(String name,String cpf, String phoneNumber, String email,String login, String password, UserRoles userRole, DonatorType donatorType) {
		super(name,cpf, phoneNumber, email, login, password, userRole);
		this.donatorType = donatorType;
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