package ifpb.edu.br.pj.ifpbichos.model.entity;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ifpb.edu.br.pj.ifpbichos.model.enums.ComissionMemberRole;
import ifpb.edu.br.pj.ifpbichos.model.enums.DonatorType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "DONATOR", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_REGISTRATION"})})
public class Donator extends User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "USER_REGISTRATION")
	private String registration;
	@Column(name = "DONATOR_TYPE")
	private DonatorType donatorType;
	
	public Donator() {
		super();
	}
	public Donator(String name, String phoneNumber, String email, String registration, DonatorType donatorType) {
		super(name, phoneNumber, email);
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
		return "Donator [registration=" + registration + ", donatorType=" + donatorType + ", getRegistration()="
				+ getRegistration() + ", getDonatorType()=" + getDonatorType() + ", getId()=" + getId() + ", getName()="
				+ getName() + ", getPhoneNumber()=" + getPhoneNumber() + ", getEmail()=" + getEmail() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(donatorType, registration);
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
		return donatorType == other.donatorType && Objects.equals(registration, other.registration);
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		return super.getLogin();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
