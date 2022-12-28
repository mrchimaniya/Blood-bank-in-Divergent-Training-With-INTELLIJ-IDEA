package com.divergentsl.services;

import com.divergentsl.entity.Donor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@Component
//@Service
public class DonorUserDetails implements UserDetails {

	private Donor donor;
	
	public DonorUserDetails(Donor donor) {
		this.donor=donor;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities=new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(donor.getRole()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return donor.getPassword();
	}

	@Override
	public String getUsername() {
		return donor.getPhone();
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	    return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
	    return true;
	}

}
