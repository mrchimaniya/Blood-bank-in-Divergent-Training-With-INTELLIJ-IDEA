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
     public Page<Donor> showAllDonorsWithPaginationAndSorting(Pageable pageable);
	 public Donor updateDonor(Donor donor);
	 public Integer deleteDonor(String phone);
	 

}
