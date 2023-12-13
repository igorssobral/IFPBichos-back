package ifpb.edu.br.pj.ifpbichos.model.entity;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ifpb.edu.br.pj.ifpbichos.model.enums.ComissionMemberRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "COMISSION_MEMBER", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_CPF"})})
public class ComissionMember extends User implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public ComissionMember(String name, String phoneNumber, String email, String CPF) {
		super(name, phoneNumber, email);
		this.CPF = CPF;

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
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.role == ComissionMemberRole.ADMIN) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		}else
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
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
