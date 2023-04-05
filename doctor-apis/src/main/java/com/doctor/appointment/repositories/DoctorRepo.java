package com.doctor.appointment.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.appointment.entities.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Integer>{

	
	Optional<Doctor> findByEmail(String email) ;
	
	
}
