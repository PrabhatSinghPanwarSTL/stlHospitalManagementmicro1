package com.doctor.appointment.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="doctors")
public class Doctor implements UserDetails {


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Did ;
	
	@Column(nullable = false, length = 100)
	private String Dname ;
	
	@Column(unique = true)
	private String email ;
	
	private String Dpassword ;
	
	private String Dmobileno ;
	
	private String Dspeciality ;
	
	
	
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinTable(name = "user_role", 
//		joinColumns = @JoinColumn(name = "user", referencedColumnName = "did"),
//		inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id")
//			)
//	private Set<Role> roles = new HashSet<>() ;



//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		
//		List<SimpleGrantedAuthority> authorities = this.roles.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()) ;
//		
//		return authorities;
//	}
//	


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.Dpassword;
	}



	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
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



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
