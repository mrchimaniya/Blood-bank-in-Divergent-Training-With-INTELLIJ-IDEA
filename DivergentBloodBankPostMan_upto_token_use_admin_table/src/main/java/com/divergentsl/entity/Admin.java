package com.divergentsl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Admin {

   @Id
   private String name;
   private String password;
   private String role;
}
