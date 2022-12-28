package com.divergentsl.controller;

import com.divergentsl.entity.JwtRequest;
import com.divergentsl.entity.JwtResponse;
import com.divergentsl.helper.JwtUtil;
import com.divergentsl.services.DonorUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/jwt")
@Slf4j
public class JwtController {

	@Autowired
	private DonorUserDetailsService adminUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/token")
	public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{

		JwtResponse jwtResponse=null;

		this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
		  //Here In The Above Line AuthenticationException May Come, If It Come then it will go to globalexception handler
		UserDetails userDetails=this.adminUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtUtil.generateToken(userDetails); //token ssis generating

		jwtResponse=new JwtResponse();
		jwtResponse.setToken(token);
		jwtResponse.setRefreshToken(null);		//refresh token
		jwtResponse.setExpiryDate(jwtUtil.extractExpiration(token)); //expiration date
		jwtResponse.setMessage("Token Generated");
		jwtResponse.setPhone(userDetails.getUsername());

		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		Object[] objects = authorities.toArray();
		Object object = objects[0];
		String authority=object.toString();

		jwtResponse.setAuthority(authority);

		return new ResponseEntity(jwtResponse,HttpStatus.CREATED);
	}
}
