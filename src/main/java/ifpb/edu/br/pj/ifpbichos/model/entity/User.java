package ifpb.edu.br.pj.ifpbichos.model.entity;

import java.util.List;
import java.util.Objects;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_LOGIN","USER_EMAIL"})})
public abstract class User implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "USER_ID")
	private Integer id;
	@Column(name = "USER_NAME")
	private String name;

	@Column(name = "USER_CPF")
	private String CPF;
	@Column(name = "USER_PHONE_NUMBER")
	private String phoneNumber;
	@Column(name = "USER_EMAIL")
	private String email;
	@Column(name = "USER_LOGIN")
	private String login;
	@Column(name = "USER_PASSWORD")
	private String password;
	@Column(name = "USER_ROLE")
	private UserRoles userRole;
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String name,String cpf, String phoneNumber, String email, String login, String password,UserRoles userRole) {
		this.name = name;
		this.CPF = cpf;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.login = login;
		this.password = password;
		this.userRole = userRole;
	}

	@Override
	public List<SimpleGrantedAuthority> getAuthorities() {
		if(this.userRole == UserRoles.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, login, name, password, phoneNumber, userRole);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(login, other.login)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(phoneNumber, other.phoneNumber) && userRole == other.userRole;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ",CPF= "+ CPF +" phoneNumber=" + phoneNumber + ", email=" + email + ", login="
				+ login + ", password=" + password + ", userRole=" + userRole + "]";
	}

	public UserRoles getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoles userRole) {
		this.userRole = userRole;
	}


	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
}
