package dmi.ris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dmi.ris.security.CustomUserDetail;
import dmi.ris.security.JWTUtil;
import dmi.ris.security.MyDetailUserService;

@RestController

public class AuthController {
	
	@Autowired
	JWTUtil jwt;
	
	@Autowired
	MyDetailUserService userservice;
	
	@Autowired
	AuthenticationManager authmgr;
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
		try {
		AuthenticationResponse res = new AuthenticationResponse();
        authmgr.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
		
        CustomUserDetail userdetails = (CustomUserDetail) userservice.loadUserByUsername(request.getUsername());
        String token = jwt.generateToken(userdetails);
        res.setJwt(token);
        
        return ResponseEntity.ok(res);
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}

}
