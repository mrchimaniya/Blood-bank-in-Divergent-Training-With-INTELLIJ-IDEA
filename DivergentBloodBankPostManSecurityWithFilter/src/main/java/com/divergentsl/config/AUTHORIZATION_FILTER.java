package com.divergentsl.config;

import com.divergentsl.helper.JwtUtil;
import com.divergentsl.services.DonorUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AUTHORIZATION_FILTER extends OncePerRequestFilter {

	@Autowired
	private DonorUserDetailsService donorUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		String s=request.getServletPath();
	   if (request.getServletPath().equals("/login"))
	   {
		 filterChain.doFilter(request, response);
	   }
	   else {
			   //get jwt
			   //check Bearer
			   //validate
			   String requestTokenHeader = request.getHeader("Authorization");
			   String username = null;
			   String jwtToken = null;

			   //check null and its format
			   if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				   jwtToken = requestTokenHeader.substring(7);
				   try {
					   username = jwtUtil.extractUsername(jwtToken);
				   } catch (Exception exception) {
					   log.error("Error While Extracting UserName From JwtToken: " + ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString(), exception);
				   }

				   //inner if()
				   //security Checkup recommended to copy
				   if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					   UserDetails userDetails = this.donorUserDetailsService.loadUserByUsername(username);
					   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					   usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					   SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				   }
			   }

			   filterChain.doFilter(request, response); //out of inner if
	   }

	}

}
