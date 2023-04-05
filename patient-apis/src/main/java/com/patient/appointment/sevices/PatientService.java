package com.patient.appointment.sevices;

import java.util.List;

import com.patient.appointment.payloads.PatientDto;

public interface PatientService {
	
	
	PatientDto registerNewPatient(PatientDto patient) ;
	

	PatientDto createPatient(PatientDto patient) ;

	PatientDto updatePatient(PatientDto patient, Integer patientId) ;
	
	PatientDto getPatientById(Integer patientId) ;
	
	List<PatientDto> getAllPatient() ;
	
	void deletePatient(Integer patientId) ;
	
	
}
