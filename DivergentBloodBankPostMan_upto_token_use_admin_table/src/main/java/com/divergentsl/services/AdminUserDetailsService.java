package com.divergentsl.services;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.divergentsl.entity.Admin;
import com.divergentsl.repo.AdminRepository;

@Service 
public class AdminUserDetailsService implements UserDetailsService{
	
	//@Autowired(required = true)
	@Autowired
	AdminRepository adminRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<Admin> admin=adminRepo.findByName(username);
		if(admin.size()==0)
		{
			throw new UsernameNotFoundException("User Not Found: "+username);
		}
		return new AdminUserDetails(admin.get(0));
	}
 
}
