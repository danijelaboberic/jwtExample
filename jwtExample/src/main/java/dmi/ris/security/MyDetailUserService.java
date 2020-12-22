package dmi.ris.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dmi.ris.model.User;
import dmi.ris.repository.UserRepository;

@Service
public class MyDetailUserService implements UserDetailsService{

	@Autowired
	UserRepository userrep;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = userrep.findByUsername(username);
		UserDetails ud = new CustomUserDetail(u);
		return ud;
	}

}
