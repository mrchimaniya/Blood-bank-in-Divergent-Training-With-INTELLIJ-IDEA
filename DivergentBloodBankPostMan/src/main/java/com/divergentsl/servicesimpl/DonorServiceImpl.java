package com.divergentsl.servicesimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.divergentsl.entity.Donor;
import com.divergentsl.repo.DonorRepository;
import com.divergentsl.services.DonorService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
@Slf4j
public class DonorServiceImpl implements DonorService{
	
    @Autowired
    DonorRepository donorRepository;
	
    @Override
	public String registerDonor(Donor donor)
	 {
		try {
			Optional<Donor> donorRetrieve= donorRepository.findByPhone(donor.getPhone());
			if(donorRetrieve.isEmpty())
			{
				Donor savedDonor = donorRepository.save(donor);
				if(savedDonor!=null)
					return "Sucess";
			}
			else {
				return "Existed";
			}
		}catch (Exception exception)
		{
			log.error("Error While Register Donor: "+ ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString(),exception);
			return "Failed";
		}

    	return "Failed";
     }

	@Override
	public Page<Donor> searchDonorByBloodGroupWithPaginationAndSorting(String bloodGroup,Pageable pageable)
	{
		Page<Donor> page=null;
		try {
			page = donorRepository.findByBloodgroup(bloodGroup, pageable);
			return page;
		  }
		catch (Exception exception)
		{
			log.error("Error While Search Donor By BloodGroup: "+ ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString(),exception);
			return page;
		}
	}

	@Override
	public Page<Donor> searchDonorByNearestHospitalWithPaginationAndSorting(String bloodGroup, String nearestBloodBank,Pageable pageable)
	{
		Page<Donor> page=null;
		try{
			page=donorRepository.findByBloodgroupAndNearestbloodbank(bloodGroup, nearestBloodBank, pageable);
			return page;
		}
		catch (Exception exception)
		{
			log.error("Error While Search Donor By NearestHospital: "+ ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString(),exception);
			return page;
		}
	}


	@Override
	public Optional<Donor> searchDonorByPhone(String Phone) {
		Optional<Donor> donor=null;
		try{
			donor=donorRepository.findByPhone(Phone);
			return donor;
		}
		catch (Exception exception)
		{
			log.error("Error While Search Donor By Phone: "+ ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString(),exception);
			return donor;
		}
	}

	//this service is for admin use only
	@Override
	public Page<Donor> showAllDonorsWithPaginationAndSorting(Pageable pageable)
	{
		Page<Donor> page = null;
		try {
			page = donorRepository.findAll(pageable);
			return page;
		}
		catch (Exception exception)
		{
			log.error("Error While Search All Donors: "+ ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString(),exception);
			return page;
		}
	}

	@Override
	public Donor updateDonor(Donor donor)
	{
		Donor updatedDonor=null;
		try {
			updatedDonor = donorRepository.save(donor);
			return updatedDonor;
			}
		catch (Exception exception)
		{
			log.error("Error While Update Donor: "+ ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString(),exception);
			return updatedDonor;
		}
	}

	@Transactional
	@Override
	public Integer deleteDonor(String phone)
	{  Integer status=0;
		try {
			status = donorRepository.deleteByPhone(phone);
			return status;
		}catch (Exception exception)
		{
			log.error("Error While Delete Donor: "+ ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString(),exception);
			return status;
		}

	}

}
