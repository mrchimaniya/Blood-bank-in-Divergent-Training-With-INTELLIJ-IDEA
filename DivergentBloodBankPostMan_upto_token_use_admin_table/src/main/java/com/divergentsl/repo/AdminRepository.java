package com.divergentsl.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divergentsl.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
   Optional<Admin> findByNameAndPassword(String name,String password);
   List<Admin> findByName(String name);
}
