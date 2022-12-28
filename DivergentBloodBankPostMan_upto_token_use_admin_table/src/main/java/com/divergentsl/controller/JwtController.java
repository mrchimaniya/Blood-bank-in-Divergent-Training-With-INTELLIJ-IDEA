package com.divergentsl.controller;

import org.apache.coyote.http11.Http11AprProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divergentsl.entity.JwtRequest;
import com.divergentsl.entity.JwtResponse;
import com.divergentsl.helper.JwtUtil;
import com.divergentsl.services.AdminUserDetailsService;

@RestController
@RequestMapping("/jwt")
public class JwtController {

	@Autowired
	private AdminUserDetailsService adminUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		try {
		    	this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	throw new Exception("Bad Credentials");
	        } 
		
		UserDetails userDetails=this.adminUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtUtil.generateToken(userDetails);
		System.out.println("The Token Is= "+token);
		System.out.println(userDetails.getAuthorities());
		ResponseEntity.ok(HttpStatus.CREATED);
		
		//key=token after colon value  {"token":"value"}
		return ResponseEntity.ok(new JwtResponse(token)); 
		
		
	}
}
