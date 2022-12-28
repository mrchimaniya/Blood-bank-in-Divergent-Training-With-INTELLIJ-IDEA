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
@RequestMapping("/")
public class PublicController {

    @Autowired
    private DonorService donorService;


//********************************************************************************
//                 FORM RENDERING
//********************************************************************************

    @GetMapping("/")
    public ResponseRender homeForm()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Home Page");
        responseRender.setStatusCode(Response.SC_OK);
        return responseRender;
    }

    @GetMapping("/donateForm")
    public ResponseRender donateForm()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Donate Page");
        responseRender.setStatusCode(Response.SC_OK);
        return responseRender;
    }

    @GetMapping("/searchForm")
    public ResponseRender searchForm()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Search Page For Choices Area Or Hospital");
        responseRender.setStatusCode(Response.SC_OK);
        return responseRender;
    }

    @GetMapping("/loginForm")
    public ResponseRender loginForm()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Login Page");
        responseRender.setStatusCode(Response.SC_OK);
        return responseRender;
    }

    @GetMapping("/unregisterForm")
    public ResponseRender unregisterForm()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Unregister Page");
        responseRender.setStatusCode(Response.SC_OK);
        return responseRender;
    }

    @GetMapping("/supportForm")
    public ResponseRender supportForm()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Support Page");
        responseRender.setStatusCode(Response.SC_OK);
        return responseRender;
    }

    @GetMapping("/searchBloodAnywhereForm")
    public ResponseRender searchBloodByArea()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Search Blood Anywhere Page");
        responseRender.setStatusCode(Response.SC_OK);
        return responseRender;
    }

    @GetMapping("/searchBloodInHospitalForm")
    public ResponseRender searchBloodByHospital()
    {
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage("Display Search Blood In Hospital Page");
        responseRender.setStatusCode(Response.SC_OK);
        return responseRender;
    }
//********************************************************************************
//                 PUBLIC OPERATIONS
//********************************************************************************
    @PostMapping("/donorRegisterRequest")
    public ResponseRender donorRegisterRequest(@RequestBody Donor donor)
    {
        donor.setRole("USER");
        String status=donorService.registerDonor(donor);
        ResponseRender responseRender=new ResponseRender();
        if(status.equalsIgnoreCase("Sucess")) {
            responseRender.setStatusCode(Response.SC_CREATED);
            responseRender.setMessage("Record Created Sucessfully");
            responseRender.setObject(donor);
        }
        if(status.equalsIgnoreCase("Existed")) {
            responseRender.setStatusCode(Response.SC_CONFLICT);
            responseRender.setMessage("Record Existed Already");
            responseRender.setObject(donor);
        }
        else {
            responseRender.setStatusCode(Response.SC_NOT_IMPLEMENTED);
            responseRender.setMessage("Record Creation Failure");
            responseRender.setObject(donor);
        }
        return responseRender;
    }

    @GetMapping("/searchBloodAnywhereRequest/{currentPage}")
    public ResponseRender searchBloodAnywhereForm(@PathVariable int currentPage,
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
           responseRender.setStatusCode(Response.SC_NOT_FOUND);
           responseRender.setMessage("No Record Found Of "+bloodGroup+" At Anylocation");
           return responseRender;
        }
        responseRender.setStatusCode(Response.SC_FOUND);
        responseRender.setMessage("All Donor's Record Of "+bloodGroup);
        responseRender.setObject(page);
        return responseRender;
    }

    @GetMapping("/searchBloodInHospitalRequest/{currentPage}")
    public ResponseRender searchBloodInHospitalRequest(@PathVariable int currentPage,
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
            responseRender.setStatusCode(Response.SC_NOT_FOUND);
            responseRender.setMessage("No Record Found Of "+bloodGroup+" At "+nearestBloodBank);
            return responseRender;
        }
        responseRender.setStatusCode(Response.SC_FOUND);
        responseRender.setMessage("All Donor's Record Of "+bloodGroup+"At "+nearestBloodBank);
        responseRender.setObject(page);
        return responseRender;
    }

    /*
    @GetMapping("/unregisterSearchUser")
    public ResponseRender unregisterSearchUser(String phone)
    {
        Optional<Donor> donorData = donorService.searchDonorByPhone(phone);
        ResponseRender responseRender=new ResponseRender();
        if(donorData.isEmpty())
        {
            responseRender.setStatusCode(Response.SC_NOT_FOUND);
            responseRender.setMessage("No Record Found Of "+phone);
            return responseRender;
        }
        responseRender.setStatusCode(Response.SC_FOUND);
        responseRender.setMessage("Record Found Of "+phone);
        responseRender.setObject(donorData.get());
        return responseRender;
    }
    */
}
