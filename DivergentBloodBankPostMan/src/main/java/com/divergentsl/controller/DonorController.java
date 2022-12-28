package com.divergentsl.controller;

import com.divergentsl.entity.Donor;
import com.divergentsl.entity.ResponseRender;
import com.divergentsl.services.DonorService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/donor")
public class DonorController {

	@Autowired
	DonorService donorService;

    @Autowired
    PasswordEncoder passwordEncoder;

	@GetMapping("/getUser")
	public ResponseEntity<ResponseRender> getUser(Authentication authentication)
	{

		String phone=authentication.getName();
		Optional<Donor> donorData= donorService.searchDonorByPhone(phone);
		ResponseRender responseRender=new ResponseRender();
		if(donorData.isEmpty())
		{
			responseRender.setMessage("User Not Found");
			return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.NOT_FOUND);
		}
		Donor donor=donorData.get();
		responseRender.setMessage("User Found");
		responseRender.setObject(donor);
		return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.FOUND);
	}

	@GetMapping("/updateForm")
	public ResponseEntity<ResponseRender> updateForm(Authentication authentication)
	{
		String phone=authentication.getName();
		Optional<Donor> donor = donorService.searchDonorByPhone(phone);
		ResponseRender responseRender=new ResponseRender();
		if(donor.isEmpty())
		{
			responseRender.setMessage("User Not Found");
			return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.NOT_FOUND);
		}
		responseRender.setMessage("Display Update Form For User With Details");
		responseRender.setObject(donor.get());
		return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.OK);
	}

	@PutMapping("/updateUser")
	public ResponseEntity<ResponseRender> updateUser(Authentication authentication, @RequestBody Donor donor)
	{
		String phone=authentication.getName();
		Optional<Donor> donorStatus = donorService.searchDonorByPhone(phone);
		ResponseRender responseRender=new ResponseRender();
		if(donorStatus.isEmpty())
		{
			responseRender.setMessage("User Not Found");
			return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.NOT_FOUND);
		}

		donor.setPhone(phone);
		donor.setRole("USER");
        donor.setPassword(passwordEncoder.encode(donor.getPassword()));
		Donor updatedDonor= donorService.updateDonor(donor);
		if(updatedDonor==null)
		{
			responseRender.setMessage("Updation Failure");
			return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.NOT_MODIFIED);
		}
		responseRender.setMessage("Updation Sucess");
		responseRender.setObject(updatedDonor);
		return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.OK);
	}

	@GetMapping("/unregisterForm")
	public ResponseEntity<ResponseRender> deleteForm(Authentication authentication)
	{
        String phone=authentication.getName();
		Optional<Donor> donor = donorService.searchDonorByPhone(phone);
		ResponseRender responseRender = new ResponseRender();
		if(donor.isEmpty()) {
			responseRender.setMessage("User Not Found");
			return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.NOT_FOUND);
		}
		responseRender.setMessage("Display Confirm Page 'YES' or 'NO'");
		responseRender.setObject(donor.get());
		return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.OK);
	}

	@DeleteMapping("/unregisterRequest")
	public ResponseEntity<ResponseRender> deleteUser(Authentication authentication)
	{
        String phone=authentication.getName();
		Optional<Donor> donorData= donorService.searchDonorByPhone(phone);
		ResponseRender responseRender = new ResponseRender();
		if(donorData.isEmpty())
		{
			responseRender.setMessage("User Not Found");
		    return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.NOT_FOUND);
		}
		Integer status = donorService.deleteDonor(phone);
		if(status==1)
		{
			responseRender.setMessage("User Deleted Sucessfully");
			return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.OK);
		}
		responseRender.setMessage("User Deletion Failure");
		return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.NOT_MODIFIED);
	}
	
}
