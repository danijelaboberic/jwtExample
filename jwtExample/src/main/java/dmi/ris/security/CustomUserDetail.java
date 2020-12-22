package dmi.ris.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dmi.ris.model.Role;
import dmi.ris.model.User;
import dmi.ris.model.UsersRole;

public class CustomUserDetail implements UserDetails{
	private User u;

	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	public CustomUserDetail(User users) {
		this.u = users;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (UsersRole r: u.getUsersRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getRole().getValue()));

		}
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return u.getPassword();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return u.getUsername();
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
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

}
