package com.patient.appointment.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.appointment.entities.Patient;

public interface PatientRepo extends JpaRepository<Patient, Integer> {

	Optional<Patient> findByEmail(String email) ;
	
}
