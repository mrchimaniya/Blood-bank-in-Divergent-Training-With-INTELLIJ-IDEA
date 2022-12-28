package com.divergentsl.config;


import com.divergentsl.helper.JwtUtil;
import com.divergentsl.services.DonorUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Response;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class AUTHENTICATION_FILTER extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public AUTHENTICATION_FILTER(AuthenticationManager authenticationManager) {
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);
        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {

        UserDetails donorUserDetails=(DonorUserDetails) authentication.getPrincipal();
        JwtUtil jwtUtil=new JwtUtil();
        String token=jwtUtil.generateToken(donorUserDetails); //token ssis generating

                Collection<? extends GrantedAuthority> authorities = donorUserDetails.getAuthorities();
                Object[] objects = authorities.toArray();
                Object object = objects[0];
                String authority=object.toString();

        response.setHeader("Acess Token",token);
        response.setHeader("Refresh Token","Refresh Toke ");
        response.setDateHeader("Expiration Date",jwtUtil.extractExpiration(token).getTime());
        response.setHeader("Phone",donorUserDetails.getUsername());
        response.setHeader("Authority",authority);
        response.setStatus(Response.SC_CREATED);


        new ObjectMapper().writeValue(response.getOutputStream(),response);
    }

}
