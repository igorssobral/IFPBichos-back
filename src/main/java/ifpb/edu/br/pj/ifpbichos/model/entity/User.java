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
	private Long id;

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

	public User() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.userRole == UserRoles.ADMIN)
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}


}


