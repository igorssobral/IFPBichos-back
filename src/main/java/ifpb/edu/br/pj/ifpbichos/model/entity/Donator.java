package ifpb.edu.br.pj.ifpbichos.model.entity;

import ifpb.edu.br.pj.ifpbichos.model.enums.DonatorType;
import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Objects;

@Entity
@Table(name = "DONATOR", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_CPF"})})
public class Donator extends User {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "DONATOR_TYPE")
	private DonatorType donatorType;

	public Donator() {
	}
	public Donator(String name,String cpf, String phoneNumber, String email,String login, String password, UserRoles userRole, DonatorType donatorType) {
		super(name,cpf, phoneNumber, email, login, password, userRole);
		this.donatorType = donatorType;
	}

	public DonatorType getDonatorType() {
		return donatorType;
	}

	public void setDonatorType(DonatorType donatorType) {
		this.donatorType = donatorType;
	}

	

	@Override
	public String toString() {
		return "Donator [donatorType=" + donatorType + "]";
	}
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(donatorType);
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
		Donator other = (Donator) obj;
		return donatorType == other.donatorType;
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