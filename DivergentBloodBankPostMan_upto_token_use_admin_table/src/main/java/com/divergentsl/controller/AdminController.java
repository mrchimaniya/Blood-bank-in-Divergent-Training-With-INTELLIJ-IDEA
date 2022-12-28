package com.divergentsl.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.divergentsl.entity.Donor;
import com.divergentsl.services.AdminUserDetailsService;
import com.divergentsl.services.DonorService;

@RestController
@RequestMapping("/admin")
public class AdminController {
   
	@Autowired
	AdminUserDetailsService adminUserDetailsService;
	
	@Autowired
	DonorService donorService;
	
		@GetMapping("/adminlogin")
		public String adminLogin()
		{
			return "adminlogin";
		}
		
		@GetMapping("/admincontrol")
		public String adminControl(String name,String password,Model model)
		{
			UserDetails loadUserByUsername = adminUserDetailsService.loadUserByUsername(name);
			if(loadUserByUsername!=null)
     			return "admincontrol";
			model.addAttribute("status","Opps! Admin Not Found");
			return "status";
		}
		
		//pagination
		@GetMapping("/alldonors/{currentPage}")
		public String allUsers(@PathVariable int currentPage,@RequestParam(required = false) String sortField ,
				               @RequestParam(required = false) String sortDir, Model model)
		{			
	        Sort sort=sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending(): Sort.by(sortField).descending();
	     	Pageable pageable=PageRequest.of(currentPage,3,sort);
	        Page<Donor> page = donorService.showAllDonorsWithPaginationAndSorting(pageable);
			List<Donor> donorsRecord=page.getContent();
			model.addAttribute("donorsRecord",donorsRecord);
			
			model.addAttribute("currentPage",currentPage);
			model.addAttribute("totalPages",page.getTotalPages());
			
			model.addAttribute("sortField",sortField);
			model.addAttribute("sortDir",sortDir);
			model.addAttribute("reverseDir",sortDir.equalsIgnoreCase("asc") ? "desc" : "asc");
			
			return "donorrecordtableforadmin";	
		}
		
		
		@GetMapping("/adminphoneform")
		public String adminPhoneForm()
		{
			return "adminphoneform";
		}
		
		@GetMapping("/donorsbyphone")
		public String usersByPhone(String phone,Model model)
		{
			Optional<Donor> donor = donorService.searchDonorByPhone(phone);
			if(donor.isEmpty())
			{    
				model.addAttribute("adminStatus","Opps! No Donors Found");
				return "adminstatus";
			}
			List<Donor> donorsRecord=new ArrayList<>();
			donorsRecord.add(donor.get());
			model.addAttribute("donorsRecord",donorsRecord);
			return "donorrecordtableforadminphone";
		}
		
		@GetMapping("/adminbloodform")
		public String adminBloodForm()
		{
			return "adminbloodform";
		}
	    
		@GetMapping("/donorsbyblood/{currentPage}")
		public String donorsByBlood(@PathVariable int currentPage,
				                    @RequestParam(required = false) String sortField ,
	                                @RequestParam(required = false) String sortDir,
	                                @RequestParam(required = false) String bloodGroup,
	                                Model model)
		{  			
			Sort sort=sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending(): Sort.by(sortField).descending();
	     	Pageable pageable=PageRequest.of(currentPage,3,sort);
	        Page<Donor> page = donorService.searchDonorByBloodGroupWithPaginationAndSorting(bloodGroup,pageable);
			List<Donor> donorsRecord=page.getContent();
			model.addAttribute("donorsRecord",donorsRecord);
	        
			model.addAttribute("currentPage",currentPage);
			model.addAttribute("totalPages",page.getTotalPages());
			
			model.addAttribute("bloodGroup",bloodGroup);
			model.addAttribute("sortField",sortField);
			model.addAttribute("sortDir",sortDir);
			model.addAttribute("reverseDir",sortDir.equalsIgnoreCase("asc") ? "desc" : "asc");
			
			return "donorrecordtableforadminblood";
		}
		
		
		@GetMapping("/admineditdonorform")
		public String adminEditDonorForm(String phone,Model model)
		{
			Optional<Donor> donorData = donorService.searchDonorByPhone(phone);
			if(donorData.isEmpty())
			{   
				model.addAttribute("adminStatus","Opps! No Donors Found");
				return "adminstatus";
			}
			Donor donor=donorData.get();
			model.addAttribute("donor",donor);
			return "admineditdonorform";
		}
		
		@PostMapping("admineditdonor")
		public String adminEditDonor(Donor donor,String oldphone,Model model)
		{
			if(oldphone.equals(donor.getPhone())==false)
			{
				donorService.deleteDonor(oldphone);
			}
			String status=donorService.updateDonor(donor);	
			model.addAttribute("adminStatus","Updation Sucess");
			return "adminstatus";	
		}
		
		@GetMapping("/admindeleteconfirm")
		public String adminDeleteForm(String name,String phone,Model model)
		{
			model.addAttribute("name",name);
			model.addAttribute("phone",phone);
			return "admindeleteconfirm";
		}
		
		@GetMapping("/adminno")
		public String adminNo()
		{
			return "admincontrol";
		}
		
		@PostMapping("adminyes")
		public String adminYes(String phone,Model model)
		{
		   Integer check=donorService.deleteDonor(phone);
		   if(check==1)
			{
			   model.addAttribute("adminStatus","Donor Deleted Sucessfully");
			   return "adminstatus";
			}
		   else
			  model.addAttribute("adminStatus","Something Went Wrong!<br>Deletion Failure");
		      return "adminstatus";
			
		}

}
