package ifpb.edu.br.pj.ifpbichos.model.entity;

import java.util.Objects;

import ifpb.edu.br.pj.ifpbichos.model.enums.DonatorType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "DONATOR", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_REGISTRATION"})})
public class Donator extends User {
	
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
	
}
