package com.divergentsl.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divergentsl.entity.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor, String> {

	//pagination
	Page<Donor> findByBloodgroup(String bloodgroup,Pageable pageable);
	Page<Donor> findByBloodgroupAndNearestbloodbank(String bloodGroup, String nearestBloodBank, Pageable pageable);

	//normal
	Optional<Donor> findByPhone(String phone);
	Integer deleteByPhone(String phone);
}
