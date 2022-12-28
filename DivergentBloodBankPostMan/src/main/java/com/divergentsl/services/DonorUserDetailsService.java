package com.divergentsl.services;

import java.util.Optional;

import com.divergentsl.entity.Donor;
import com.divergentsl.repo.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service 
public class DonorUserDetailsService implements UserDetailsService{
	
	//@Autowired(required = true)
	@Autowired
	DonorRepository donorRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Donor> donor = donorRepo.findByPhone(username);
		if(donor.isEmpty())
		{
			throw new UsernameNotFoundException("User Not Found: "+username);
		}
		return new DonorUserDetails(donor.get());
	}
 
}
