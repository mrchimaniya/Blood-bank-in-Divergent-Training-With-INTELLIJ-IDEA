package com.divergentsl.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Donor extends Audit {
   
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

	@JsonProperty( access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String role;

	@PrePersist
	public void createdBy()
	{
		if(getCreatedBy().equals("anonymousUser") || getCreatedBy().equals("anonymousUser ROLE_ANONYMOUS")) {
			setCreatedBy("Self " + getPhone());
			setLastModifiedBy("Self "+getPhone());
		}
	}

}
