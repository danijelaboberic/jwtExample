package dmi.ris.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JWTUtil {

	@Value("${secret_key}")
	private String secret;
	
	 private SecretKey secretKey;
	    
	 @PostConstruct
	 public void init() {
	     this.secret = Base64.getEncoder().encodeToString(this.secret.getBytes());
	     this.secretKey = Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_8));
	 }
	 
	public String generateToken(UserDetails user) {
	        ClaimsBuilder claimsBuilder = Jwts.claims().subject(user.getUsername());
	        Claims claims = claimsBuilder.build();
	        Date now = new Date();
	        Date validity = new Date(System.currentTimeMillis()+1000*60*60);
	        
	        return Jwts.builder()
	                .claims(claims)
	                .issuedAt(now)
	                .expiration(validity)
	                .signWith(secretKey, Jwts.SIG.HS256)
	                .compact();
					
	}
	
    public String extractUsername(String token) {
        Claims claims = Jwts.parser().verifyWith(this.secretKey).build().parseSignedClaims(token).getPayload();     
        return claims.getSubject();       
    }
	

}
