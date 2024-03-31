package com.example.test.User.DTO;

import java.util.ArrayList;
import java.util.Collection;

import javax.management.relation.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @Builder
public class UserDTO implements UserDetails{
	
	private Integer UID;	//p
	
	private String userId;  
	
	private String userName; 
	
	private Integer mbti;   //FK
	
	private String BNum;
	
	private String password;

	private String phone;
	
	private String Rank;
	
	private String mail;
	
	private String cart;
	
	private String history;
	
	private String userrole;
	
	@Override
	   public String toString() {
	      return "UserDTO [userId=" + userId + ", password=" + password + ", "
	         + "userName=" + userName + ", mail=" + mail
	         + ", phone=" + phone+ 
	         ", mbti=" +mbti+"]"+ ", admin= "+ userrole
	         + ", BNum =" + BNum;    
	   }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		
		for(User_Role role : User_Role.values()) {
			authorities.add(new SimpleGrantedAuthority(role.getValue()));
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
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

	public Integer UID() {
		return this.UID;
	}
	
}
