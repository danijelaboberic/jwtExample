package dmi.ris.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthorizeFilter extends OncePerRequestFilter {

	@Autowired
	JWTUtil jwt;
	
	@Autowired
	MyDetailUserService userservice;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = request.getHeader("Authorization");
			if (token != null && token.startsWith("Bearer ")) {
				token = token.substring(7);
				String username = jwt.extractUsername(token);
				if (username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
					UserDetails user = userservice.loadUserByUsername(username);
	                if (username.equals(user.getUsername())) {
	                	UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());	
	                	authenticate.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                	SecurityContextHolder.getContext().setAuthentication(authenticate);
	                }
				}
			}
			
			filterChain.doFilter(request, response);
		}catch(Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
		}
		}


}
