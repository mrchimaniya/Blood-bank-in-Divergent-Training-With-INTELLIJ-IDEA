package com.divergentsl.controller;

import com.divergentsl.entity.Donor;
import com.divergentsl.entity.ResponseRender;
import com.divergentsl.services.DonorService;
import com.divergentsl.services.DonorUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
@Lazy
public class AdminController {

	@Autowired
	DonorUserDetailsService adminUserDetailsService;

	@Autowired
	DonorService donorService;


	//pagination
	@GetMapping("/allUsers/{currentPage}")
	public ResponseEntity<ResponseRender> allUsers(@PathVariable int currentPage, @RequestParam(required = false) String sortField,
								   @RequestParam(required = false) String sortDir, Model model) {
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(currentPage, 3, sort);
		Page<Donor> page = donorService.showAllDonorsWithPaginationAndSorting(pageable);
		ResponseRender responseRender = new ResponseRender();
		if (page.getContent().isEmpty()) {
			responseRender.setMessage("No Record Found");
			return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.NOT_FOUND);
		}
		responseRender.setMessage("All Available Donor's");
		responseRender.setObject(page);
		return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.FOUND);
	}

	@GetMapping("/adminBloodForm")
	public ResponseEntity<ResponseRender> adminBloodForm() {
		ResponseRender responseRender = new ResponseRender();
		responseRender.setMessage("Display Admin Blood Selection Form With Delete User Button");
		return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.OK);
	}

	@GetMapping("/usersByBlood/{currentPage}")
	public ResponseEntity<ResponseRender> usersByBlood(@PathVariable int currentPage,
									   @RequestParam(required = false) String sortField,
									   @RequestParam(required = false) String sortDir,
									   @RequestParam(required = false) String bloodGroup) {
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(currentPage, 3, sort);
		Page<Donor> page = donorService.searchDonorByBloodGroupWithPaginationAndSorting(bloodGroup, pageable);
		ResponseRender responseRender = new ResponseRender();
		if (page.getContent().isEmpty()) {
			responseRender.setMessage("No Record Found For " + bloodGroup);
			return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.NOT_FOUND);
		}
		responseRender.setMessage("Available Donor's Of Blood " + bloodGroup);
		responseRender.setObject(page);
		return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.FOUND);
	}

	@GetMapping("/adminPhoneForm")
	public ResponseEntity<ResponseRender> adminPhoneForm() {
		ResponseRender responseRender = new ResponseRender();
		responseRender.setMessage("Display Admin Phone Form With Delete User Button");
		return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.OK);
	}

	@GetMapping("/usersByPhone")
	public ResponseEntity<ResponseRender> usersByPhone(String phone) {
		Optional<Donor> donor = donorService.searchDonorByPhone(phone);
		ResponseRender responseRender = new ResponseRender();
		if (donor.isEmpty()) {
			responseRender.setMessage("No Record Found For " + phone);
			return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.NOT_FOUND);
		}
		responseRender.setMessage("Available Record For " + phone);
		responseRender.setObject(donor.get());
		return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.FOUND);
	}

	@GetMapping("/unregisterForm")
	public ResponseEntity<ResponseRender> deleteForm(String phone) {
		Optional<Donor> donor = donorService.searchDonorByPhone(phone);
		ResponseRender responseRender = new ResponseRender();
		if (donor.isEmpty()) {
			responseRender.setMessage("User Not Found");
			return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.NOT_FOUND);
		}
		responseRender.setMessage("Display Confirm Page 'YES' or 'NO'");
		responseRender.setObject(donor.get());
		return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.OK);
	}

	@DeleteMapping("/unregisterRequest")
	public ResponseEntity<ResponseRender> deleteUser(String phone) {
		Optional<Donor> donorData = donorService.searchDonorByPhone(phone);
		ResponseRender responseRender = new ResponseRender();
		if (donorData.isEmpty()) {
			responseRender.setMessage("User Not Found");
			return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.NOT_FOUND);
		}
		Integer status = donorService.deleteDonor(phone);
		if (status == 1) {
			responseRender.setMessage("User Deleted Sucessfully");
			return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.OK);
		}
		responseRender.setMessage("User Deletion Failure");
		return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.NOT_MODIFIED);
	}

	@PutMapping("/changeRole")
	public ResponseEntity<ResponseRender> updateUser(String phone,String role)
	{
		Optional<Donor> donorStatus = donorService.searchDonorByPhone(phone);
		ResponseRender responseRender=new ResponseRender();
		if(donorStatus.isEmpty())
		{
			responseRender.setMessage("User Not Found");
			return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.NOT_FOUND);
		}

		if(role.equals("ADMIN"))
		{
			Donor donor=donorStatus.get();
			donor.setRole("ADMIN");
			Donor updateDonor = donorService.updateDonor(donor);
			if(updateDonor!=null)
			{
				responseRender.setMessage("Updation Sucess");
				responseRender.setObject(updateDonor);
				return new ResponseEntity<>(responseRender,HttpStatus.OK);
			}
		}

		if(role.equals("USER"))
		{
			Donor donor=donorStatus.get();
			donor.setRole("USER");
			Donor updateDonor = donorService.updateDonor(donor);
			if(updateDonor!=null)
			{
				responseRender.setMessage("Updation Sucess");
				responseRender.setObject(updateDonor);
				return new ResponseEntity<>(responseRender,HttpStatus.OK);
			}
		}
		responseRender.setMessage("Updation Failure");
		return new ResponseEntity<ResponseRender>(responseRender,HttpStatus.NOT_MODIFIED);

	}


}