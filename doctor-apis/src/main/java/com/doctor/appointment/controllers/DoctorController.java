package com.doctor.appointment.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import com.doctor.appointment.payloads.ApiResponse;
import com.doctor.appointment.payloads.DoctorDto;
import com.doctor.appointment.services.DoctorService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

	
	@Autowired
	private DoctorService doctorService ;
	
	
	//POST - create doctor
	@PostMapping("/")
	public ResponseEntity<DoctorDto> createDoctor(@Valid @RequestBody DoctorDto doctorDto) {
		
		DoctorDto createDoctorDto = this.doctorService.createDoctor(doctorDto) ;
		
		return new ResponseEntity<>(createDoctorDto, HttpStatus.CREATED) ;
	}
	
	//PUT - update doctor
	@PutMapping("/{doctorId}")
	public ResponseEntity<DoctorDto> updateDoctor(@Valid @RequestBody DoctorDto doctorDto, @PathVariable("doctorId") Integer doctorId) {
		
		DoctorDto updatedDoctor = this.doctorService.updateDoctor(doctorDto, doctorId) ;
		
		
		return ResponseEntity.ok(updatedDoctor) ;
	}
	
	//DELETE - delete doctor
	@DeleteMapping("/{doctorId}")
	public ResponseEntity<ApiResponse> deleteDoctor(@PathVariable Integer doctorId) {
		
		this.doctorService.deleteDoctor(doctorId) ;
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Doctor Deleted Successfully", true), HttpStatus.OK) ;
		
	}
	
	
	//GET - get doctor
	@GetMapping("/")
	public ResponseEntity<List<DoctorDto>> getAllDoctors() {
		
		return ResponseEntity.ok(this.doctorService.getAllDoctor()) ;
		
	}
	
	@GetMapping("/{doctorId}")
	public ResponseEntity<DoctorDto> getSingleDoctor(@PathVariable Integer doctorId) {
		
		return ResponseEntity.ok(this.doctorService.getDoctorById(doctorId)) ;
		
	}
	
	
}
