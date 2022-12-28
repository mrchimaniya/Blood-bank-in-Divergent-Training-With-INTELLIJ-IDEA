package com.divergentsl.controller;

import com.divergentsl.entity.Donor;
import com.divergentsl.entity.ResponseRender;
import com.divergentsl.services.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class PublicController {

    @Autowired
    private DonorService donorService;

    @Autowired
    private PasswordEncoder passwordEncoder;


//********************************************************************************
//                 FORM RENDERING
//********************************************************************************

    @GetMapping("/")
    public ResponseEntity<ResponseRender> homeForm()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Home Page");
        return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.OK);
    }

    @GetMapping("/donateForm")
    public ResponseEntity<ResponseRender> donateForm()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Donate Page");
        return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.OK);
    }

    @GetMapping("/searchForm")
    public ResponseEntity<ResponseRender> searchForm()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Search Page For Choices Area Or Hospital");
        return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.OK);
    }

    @GetMapping("/loginForm")
    public ResponseEntity<ResponseRender> loginForm()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Login Page");
        return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.OK);
    }

    @GetMapping("/unregisterForm")
    public ResponseEntity<ResponseRender> unregisterForm()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Unregister Page");
        return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.OK);
    }

    @GetMapping("/supportForm")
    public ResponseEntity<ResponseRender> supportForm()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Support Page");
        return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.OK);
    }

    @GetMapping("/searchBloodAnywhereForm")
    public ResponseEntity<ResponseRender> searchBloodByArea()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Search Blood Anywhere Page");
        return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.OK);
    }

    @GetMapping("/searchBloodInHospitalForm")
    public ResponseEntity<ResponseRender> searchBloodByHospital()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Search Blood In Hospital Page");
        return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.OK);
    }
//********************************************************************************
//                 PUBLIC OPERATIONS
//********************************************************************************
    @PostMapping("/donorRegisterRequest")
    public ResponseEntity<ResponseRender> donorRegisterRequest(@RequestBody Donor donor)
    {

        donor.setRole("USER");
        donor.setPassword(passwordEncoder.encode(donor.getPassword()));
        String status=donorService.registerDonor(donor);
        ResponseRender responseRender=new ResponseRender();
        if(status.equalsIgnoreCase("Sucess")) {
            responseRender.setMessage("Record Created Sucessfully");
            responseRender.setObject(donor);
            return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.CREATED);
        }
        if(status.equalsIgnoreCase("Existed")) {
            responseRender.setMessage("Record Existed Already");
            responseRender.setObject(donor);
            return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.CONFLICT);
        }
        responseRender.setMessage("Record Creation Failure");
        responseRender.setObject(donor);
        return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/searchBloodAnywhereRequest/{currentPage}")
    public ResponseEntity<ResponseRender> searchBloodAnywhereForm(@PathVariable int currentPage,
                                  @RequestParam(required = false) String sortField ,
                                  @RequestParam(required = false) String sortDir,
                                  @RequestParam String bloodGroup)
    {
        Sort sort=sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending(): Sort.by(sortField).descending();
        Pageable pageable= PageRequest.of(currentPage,3,sort);
        Page<Donor> page = donorService.searchDonorByBloodGroupWithPaginationAndSorting(bloodGroup,pageable);
        ResponseRender responseRender=new ResponseRender();
        if(page.getContent().isEmpty())
        {
           responseRender.setMessage("No Record Found Of "+bloodGroup+" At Anylocation");
            return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.NOT_FOUND);
        }
        responseRender.setMessage("All Donor's Record Of "+bloodGroup);
        responseRender.setObject(page);
        return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.FOUND);
    }

    @GetMapping("/searchBloodInHospitalRequest/{currentPage}")
    public ResponseEntity<ResponseRender> searchBloodInHospitalRequest(@PathVariable int currentPage,
                                             @RequestParam(required = false) String sortField ,
                                             @RequestParam(required = false) String sortDir,
                                             @RequestParam String bloodGroup,
                                             @RequestParam String nearestBloodBank)
    {
        Sort sort=sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending(): Sort.by(sortField).descending();
        Pageable pageable=PageRequest.of(currentPage,3,sort);
        Page<Donor> page = donorService.searchDonorByNearestHospitalWithPaginationAndSorting(bloodGroup, nearestBloodBank, pageable);
        ResponseRender responseRender=new ResponseRender();
        if(page.getContent().isEmpty())
        {
            responseRender.setMessage("No Record Found Of "+bloodGroup+" At "+nearestBloodBank);
            return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.NOT_FOUND);
        }
        responseRender.setMessage("All Donor's Record Of "+bloodGroup+"At "+nearestBloodBank);
        responseRender.setObject(page);
        return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.FOUND);
    }
}
