package com.patient.appointment.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.patient.appointment.entities.Apointment;
import com.patient.appointment.payloads.ApiResponse;
import com.patient.appointment.payloads.PatientDto;
import com.patient.appointment.sevices.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private PatientService patientService ;
	
	@Autowired
	private RestTemplate restTemplate ;
	
	
	
	//POST - create user
	@PostMapping("/")
	public ResponseEntity<PatientDto> createPatient(@Valid @RequestBody PatientDto patientDto) {
		
		PatientDto createPatientDto = this.patientService.createPatient(patientDto) ;
		
		return new ResponseEntity<>(createPatientDto, HttpStatus.CREATED) ;
		
	}
	
	//PUT - update user
	@PutMapping("/{patientId}")
	public ResponseEntity<PatientDto> updateUser(@Valid @RequestBody PatientDto patientDto, @PathVariable("patientId") Integer patientId) {
		
		PatientDto updatedPatient = this.patientService.updatePatient(patientDto, patientId) ;
		
		return ResponseEntity.ok(updatedPatient) ;
	}
	
	//DELETE - delete user
	@DeleteMapping("/{patientId}")
	public ResponseEntity<ApiResponse> deletePatient(@PathVariable("patientId") Integer patientId) {
		
		this.patientService.deletePatient(patientId) ;
		return new ResponseEntity<ApiResponse>(new ApiResponse("Patient Deleted Successfully", true), HttpStatus.OK) ;
		
	}
	
	//GET - get user
	@GetMapping("/")
	public ResponseEntity<List<PatientDto>> getAllUsers() {
		
		return ResponseEntity.ok(this.patientService.getAllPatient()) ;
		
	}
	
	@GetMapping("/{patientId}")
	public ResponseEntity<PatientDto> getSingleUsers(@PathVariable Integer patientId) {
		
		return ResponseEntity.ok(this.patientService.getPatientById(patientId)) ;
		
	}
	
	
	//for booking appointment for the patient
	@PostMapping("/addappointment")
    public Apointment addAppointment(@RequestBody Apointment app) {
        return restTemplate.postForObject("http://localhost:2023/api/apointments/", app, Apointment.class);
        
    }
	
	//Delete Appointment By Aid
	@DeleteMapping("/deleteAppointment/{Aid}")
    public ApiResponse deleteAppointment(@PathVariable Long Aid) {
        Map<String,Long> param=new HashMap<String,Long>();
        restTemplate.delete("http://localhost:2023/api/apointments/"+Aid);
        
        return new ApiResponse("user Deleted",true);
    }
	
	//for updating appointment for patient using Aid
//	@PutMapping("/updateappointment/{Aid}")
//	public ResponseEntity<Apointment> updateApointment(@RequestBody Apointment apointment, Integer Aid) {
//		
//		Apointment updatedApointment = this.patientService.updatePatient(null, Aid)
//		
//		Apointment updatedApointment = restTemplate.put("http://localhost:2023/api/apointments/", Aid, Apointment.class) ;
//		
//		return null ;
//	}
//	
}
