package com.doctor.appointment.services;

import java.util.List;

import com.doctor.appointment.payloads.DoctorDto;

public interface DoctorService {
	
	
	DoctorDto registerNewDoctor(DoctorDto doctor) ;
	

	DoctorDto createDoctor(DoctorDto doctor) ;

	DoctorDto updateDoctor(DoctorDto doctor, Integer doctorId) ;
	
	DoctorDto getDoctorById(Integer doctorId) ;
	
	List<DoctorDto> getAllDoctor() ;
	
	void deleteDoctor(Integer doctorId) ;
	
	
}
