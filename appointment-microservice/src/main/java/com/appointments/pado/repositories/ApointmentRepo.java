package com.appointments.pado.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appointments.pado.entities.Apointment;
import com.appointments.pado.payloads.ApointmentDto;

public interface ApointmentRepo extends JpaRepository<Apointment, Integer> {

	@Query(value="SELECT apointment.aid,\r\n"
			+ "apointment.date,apointment.slot,\r\n"
			+ "doctors.dname as DOCTOR,doctors.email,doctors.dspeciality,\r\n"
			+ "patients.name as PATIENT, patients.email\r\n"
			+ "FROM doctors  JOIN apointment \r\n"
			+ "ON apointment.doctorid=doctors.did\r\n"
			+ "JOIN patients\r\n"
			+ "On apointment.patientid=patients.id where patients.id=?1", nativeQuery = true)
	List<String> findApointmentByPatientid(Integer patient_id) ;
	
	
	@Query(value="SELECT apointment.aid,\r\n"
			+ "apointment.date,apointment.slot,\r\n"
			+ "doctors.dname as DOCTOR,doctors.email,doctors.dspeciality,\r\n"
			+ "patients.name as PATIENT, patients.email\r\n"
			+ "FROM doctors  JOIN apointment \r\n"
			+ "ON apointment.doctorid=doctors.did\r\n"
			+ "JOIN patients\r\n"
			+ "On apointment.patientid=patients.id where doctors.did=?1", nativeQuery = true)
	List<String> findApointmentByDoctorid(Integer doctor_id) ;
	
	
	@Query(value="select * from apointment where apointment.doctorid=?1 and slot = ?2 and date = ?3", nativeQuery = true)
	List<Apointment> findApointmentByslot(int doctorid, String slot, Date date) ;
}
