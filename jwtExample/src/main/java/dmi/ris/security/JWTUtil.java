package dmi.ris.security;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity.JwtSpec;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {

	@Value("${secret_key}")
	private String secret;
	
	public String generateToken(UserDetails user) {
	 return Jwts.builder().setClaims(new HashMap<String,Object>()).
							setSubject(user.getUsername()).
							setIssuedAt(new Date()).
							setExpiration(new Date(System.currentTimeMillis()+1000*60*60)).
							signWith(SignatureAlgorithm.HS256, secret).compact();
			
	}
	
	private Claims extractAllClaims(String token){
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}
	public boolean validateToken(String token, UserDetails user) {
		return extractUsername(token).equals(user.getUsername());
		
	}
}
