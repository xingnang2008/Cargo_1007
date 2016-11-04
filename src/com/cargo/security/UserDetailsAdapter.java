package com.cargo.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.cargo.model.Role;
import com.cargo.model.User;

@SuppressWarnings("deprecation")
public class UserDetailsAdapter implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	public UserDetailsAdapter(User user){
		this.user = user;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		 Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		    for (Role r :user.getRoles()) {
		        authorities.add(new GrantedAuthorityImpl(r.getName()));
		    }
		    return authorities;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.isEnabled();
	}
	
}
