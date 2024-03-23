package ifpb.edu.br.pj.ifpbichos.model.entity;

import ifpb.edu.br.pj.ifpbichos.model.enums.DonatorType;
import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Objects;

@Entity
@Table(name = "DONATOR", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_REGISTRATION"})})
public class Donator extends User {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "USER_REGISTRATION")
	private String registration;
	@Column(name = "DONATOR_TYPE")
	private DonatorType donatorType;

	public Donator() {
	}
	public Donator(String name,String cpf, String phoneNumber, String email,String login, String password, UserRoles userRole, String registration, DonatorType donatorType) {
		super(name,cpf, phoneNumber, email, login, password, userRole);
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

	@Override
	public String toString() {
		return super.toString() + " - Donator{" + "registration='" + registration + '\'' + ", donatorType=" + donatorType + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Donator donator)) return false;
		if (!super.equals(o)) return false;
		return Objects.equals(getRegistration(), donator.getRegistration()) && getDonatorType() == donator.getDonatorType();
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getRegistration(), getDonatorType());
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