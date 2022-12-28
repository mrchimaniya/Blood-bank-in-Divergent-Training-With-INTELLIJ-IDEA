package com.divergentsl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Donor {
   
	@Id
	private String phone;
	private String name;
	private String gender;
	private String age;
	private String bloodgroup;
	private String nearestbloodbank;
	private String email;
	private String address;
	private String disease;
	private String password;
	private String role;
}
