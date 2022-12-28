package com.divergentsl.controller;

import com.divergentsl.entity.Donor;
import com.divergentsl.entity.ResponseRender;
import com.divergentsl.services.DonorService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/donor")
public class DonorController {

	@Autowired
	DonorService donorService;

	@GetMapping("/userlogin")
	public String userLogin()
	{
		return "userlogin";
	}

	
	@GetMapping("/forgetpassword")
	public String forgetPassword()
	{
		return "forgetpassword";
	}
	
	@GetMapping("/confirm")
	public String confim()
	{
		return "confirm";
	}
	
	@GetMapping("/donorfound")
	public String donorFound()
	{
		return "donorfound";
	}
	
	
//	request for operations

	@GetMapping("/userloginrequest")
	public String userLoginRequest(@RequestParam String phone, @RequestParam String password, Model model)
	{   
		String result="";
		Optional<Donor> donor=donorService.userLogin(phone, password);
		if(donor.isEmpty())
		{
			model.addAttribute("status","OPPS! Record Not Found");
			return "status";
		}
		model.addAttribute(donor.get());
		return "userlogindata";
	}
	
	@GetMapping("/updateuser")
	public String updateUser(@RequestParam String phone, Model model)
	{  
		Optional<Donor> donorData= donorService.searchDonorByPhone(phone);
		Donor donor=donorData.get();
		model.addAttribute("donor",donor);
		return "updateuser";
	}
	
	@PostMapping("/updateuserdata")
	public String updateUserData(Donor donor, Model model)
	{  
		String status=donorService.updateDonor(donor);
		model.addAttribute("status",status);
		return "status";
	}
	
	@GetMapping("/forgetpassworddata")
	public String forgetPasswordData(@RequestParam String name, @RequestParam String phone, @RequestParam String bloodgroup, Model model)
	{
		Optional<Donor> donordata=donorService.forgetPassword(name, phone, bloodgroup);
		if(donordata.isEmpty())
		{
			model.addAttribute("status","<span style='font-size:30'>Opps! Record Not Found<span>");
			return "status";
		}
		Donor donor=donordata.get();
		model.addAttribute("donor",donor);
		return "showpassword";
	}
	
	@GetMapping("/confirmunregister")
	public String confirmUnRegisterDonor(String phone,String name, Model model)
	{
		model.addAttribute("phone",phone);
		model.addAttribute("name",name);
		return "confirmunregister";
	}

	@PostMapping("/unregisterdata")
	public String unRegisterData(@RequestParam String phone,@RequestParam String password,Model model)
	{
		Optional<Donor> donorData = donorService.userLogin(phone, password);
		if(donorData.isEmpty())
		{
			model.addAttribute("status","Opps! Retry<br>Your Password Was Not Matched.");
			return "wrongpassword";
		}
		
		Integer status=donorService.deleteDonor(phone);
		if(status==1)
		{
			model.addAttribute("status","Your Profile Has Been Deleted!<br>Thank You For Your Contribution");
			return "status";
		}
		model.addAttribute("Something Went Wrong! Please Try Again");	
		return "status";
	}	
	
}
