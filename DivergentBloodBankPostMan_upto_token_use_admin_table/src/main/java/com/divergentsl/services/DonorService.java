package com.divergentsl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.divergentsl.entity.Donor;

public interface DonorService {

     public String registerDonor(Donor donor);
     public Page<Donor> searchDonorByBloodGroupWithPaginationAndSorting(String bloodGroup,Pageable pageable);
	 public Page<Donor> searchDonorByNearestHospitalWithPaginationAndSorting(String bloodGroup,String nearestBloodBank,Pageable pageable);
	 public Optional<Donor> searchDonorByPhone(String Phone);


	//*****************************************
	 public Optional<Donor> userLogin(String phone,String password);
	 public String updateDonor(Donor donor);
	 public Optional<Donor> forgetPassword(String name,String phone,String bloodgroup);
	 public Integer deleteDonor(String phone); 
	 
	 
	 //pagination
public Page<Donor> showAllDonorsWithPaginationAndSorting(Pageable pageable);
}
