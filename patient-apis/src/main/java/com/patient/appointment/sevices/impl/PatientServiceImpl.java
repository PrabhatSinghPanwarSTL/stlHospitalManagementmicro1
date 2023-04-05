package com.patient.appointment.sevices.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.patient.appointment.config.AppConstants;
import com.patient.appointment.entities.Apointment;
import com.patient.appointment.entities.Patient;
//import com.patient.appointment.entities.Role;
import com.patient.appointment.exceptions.ResourceNotFoundException;
import com.patient.appointment.payloads.PatientDto;
import com.patient.appointment.repositories.PatientRepo;
//import com.patient.appointment.repositories.RoleRepo;
import com.patient.appointment.sevices.PatientService;


@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepo patientRepo ;
	
	@Autowired
	private ModelMapper modelMapper ;
	
	@Autowired
	private RestTemplate restTemplate ;
	
	@Autowired
	private PasswordEncoder passwordEncoder ;
	
//	@Autowired
//	private RoleRepo roleRepo ;
	
	
	@Override
	public PatientDto createPatient(PatientDto patientDto) {
		// TODO Auto-generated method stub
		
		Patient patient = this.dtoToPatient(patientDto) ;
		Patient savedPatient	 = this.patientRepo.save(patient) ;
		return this.patientToDto(savedPatient);
	}

	@Override
	public PatientDto updatePatient(PatientDto patientDto, Integer patientId) {
		// TODO Auto-generated method stub
		
		Patient patient = this.patientRepo.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", patientId)) ;
		
		
		patient.setName(patientDto.getName());
		patient.setEmail(patientDto.getEmail());
		patient.setPassword(patientDto.getPassword());
		patient.setMobileno(patientDto.getMobileno());
		
		Patient updatedPatient = this.patientRepo.save(patient) ;
		PatientDto  userDto1 = this.patientToDto(updatedPatient) ;
		
		return userDto1;
	}

	@Override
	public PatientDto getPatientById(Integer patientId) {
		// TODO Auto-generated method stub
			
		Patient patient = this.patientRepo.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", patientId)) ; ;
			
		ArrayList<Apointment> resultlist = restTemplate.getForObject("http://localhost:2023/api/apointments/patientApointment/" + patient.getId() , ArrayList.class) ;
			
		patient.setApointments(resultlist) ;

		
		return this.patientToDto(patient);
	}

	@Override
	public List<PatientDto> getAllPatient() {
		// TODO Auto-generated method stub
		
		List<Patient> patients= this.patientRepo.findAll() ;
		
		List<PatientDto> patientDtos = patients.stream().map(patient -> this.patientToDto(patient)).collect(Collectors.toList()) ;
		
//		ArrayList result = this.restTemplate.exchange("http://localhost:9091/api/doctors/", HttpMethod.GET, null, ArrayList.class).getBody() ;
//		System.out.println(result);
		
		return patientDtos;
	}

	@Override
	public void deletePatient(Integer patientId) {
		// TODO Auto-generated method stub

		Patient patient = this.patientRepo.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", patientId)) ;
		
		this.patientRepo.delete(patient);
		
	}
	
	
	public Patient dtoToPatient(PatientDto patientDto) {
		
		Patient patient = this.modelMapper.map(patientDto, Patient.class) ;
		
		return patient ;
	}
	
	public PatientDto patientToDto(Patient patient) {
		
		PatientDto patientDto = this.modelMapper.map(patient, PatientDto.class) ;

		return patientDto ;
	}

	@Override
	public PatientDto registerNewPatient(PatientDto patientDto) {
		// TODO Auto-generated method stub
		
		Patient patient = this.modelMapper.map(patientDto, Patient.class) ;
		
		
		//encoded the password
		patient.setPassword(this.passwordEncoder.encode(patient.getPassword()));
		
		//roles
//		Role role = this.roleRepo.findById(AppConstants.Patient_Role).get() ;
//		
//		patient.getRoles().add(role) ;
		
		Patient newPatient = patientRepo.save(patient) ;
		
		
		return this.modelMapper.map(newPatient, PatientDto.class);
	}

}
