package ifpb.edu.br.pj.ifpbichos.model.entity;


import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_LOGIN","USER_EMAIL"})})
@Data
public abstract class User implements UserDetails {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "USER_ID")
	private Integer id;

	@Column(name = "USER_NAME",nullable = false)
	private String name;

	@Column(name = "USER_CPF", unique = true, nullable = false)
	private String CPF;
	@Column(name = "USER_PHONE_NUMBER", unique = true, nullable = false)
	private String phoneNumber;
	@Column(name = "USER_EMAIL",unique = true, nullable = false)
	private String email;
	@Column(name = "USER_LOGIN",unique = true, nullable = false)
	private String login;
	@Column(name = "USER_PASSWORD",nullable = false)
	private String password;
	@Column(name = "USER_ROLE")
	private UserRoles userRole;

	@OneToMany(mappedBy = "donator")
	private List<Donation> donations;

	public User(String name, String cpf, String phoneNumber, String email, String login, String password, UserRoles userRole) {
		this.name = name;
		this.CPF = cpf;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.login = login;
		this.password = password;
		this.userRole = userRole;
	}

	public User() {
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.userRole == UserRoles.ADMIN)
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRoles getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoles userRole) {
		this.userRole = userRole;
	}


	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", CPF='" + CPF + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", email='" + email + '\'' +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", userRole=" + userRole +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(CPF, user.CPF) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(email, user.email) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && userRole == user.userRole;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, CPF, phoneNumber, email, login, password, userRole);
	}

}


