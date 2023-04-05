package com.appointments.pado.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointments.pado.AppointmentMicroserviceApplication;
import com.appointments.pado.entities.Apointment;
import com.appointments.pado.entities.ApointmentDetails;
import com.appointments.pado.payloads.ApiResponse;
import com.appointments.pado.payloads.ApointmentDto;
import com.appointments.pado.services.ApointmentServices;

@RestController
@RequestMapping("/api/apointments")
public class ApointmentController {

	
	@Autowired
	private ApointmentServices apointmentServices ;
	
	
	
	@PostMapping("/")
	@CrossOrigin(origins="http://localhost:3000")
	public ResponseEntity<ApointmentDto> createApointment(@RequestBody ApointmentDto apointmentDto) {
		
		
		
		List<Apointment> value = this.apointmentServices.presentCheck(apointmentDto.getDoctorid(), apointmentDto.getSlot(), apointmentDto.getDate()) ;
		
		System.out.println(value);
		
		if(value.isEmpty()) {
			
			System.out.println("there is not a duplicate value over here");
			
			ApointmentDto createApointmentDto = this.apointmentServices.createApointment(apointmentDto) ;
			
			return new ResponseEntity<>(createApointmentDto, HttpStatus.CREATED) ;
		}else {
			System.out.println("duplicate value present");
			return null ;
		}
		
//		ApointmentDto createApointmentDto = this.apointmentServices.createApointment(apointmentDto) ;
		
		
//		return new ResponseEntity<>(createApointmentDto, HttpStatus.CREATED) ;
//		return new ResponseEntity<>(value, HttpStatus.CREATED) ;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ApointmentDto>> getallApointments() {
		
		return ResponseEntity.ok(this.apointmentServices.getAllApoitments()) ;
		
	}
	
	@GetMapping("/{apointmentId}")
	@CrossOrigin(origins="http://localhost:3000")
	public ResponseEntity<ApointmentDto> getSinghApointment(@PathVariable Integer apointmentId) {
		
		return ResponseEntity.ok(this.apointmentServices.getApointmentByAid(apointmentId)) ;
		
	}
	
	
	
	
	@PutMapping("/{apointmentId}")
	@CrossOrigin(origins="http://localhost:3000")
	public ResponseEntity<ApointmentDto> updateApointment(@RequestBody ApointmentDto apointmentDto, @PathVariable Integer apointmentId) {
		
List<Apointment> value = this.apointmentServices.presentCheck(apointmentDto.getDoctorid(), apointmentDto.getSlot(), apointmentDto.getDate()) ;
		
		System.out.println(value);
		
		if(value.isEmpty()) {
			
			System.out.println("there is not a duplicate value over here");
			
			ApointmentDto updateApoinment = this.apointmentServices.updateApointment(apointmentDto, apointmentId) ;
			
			return ResponseEntity.ok(updateApoinment) ;
		}else {
			System.out.println("duplicate value present");
			return null ;
		}
		
		
	}
	
	
	@DeleteMapping("/{apointmentId}")
	@CrossOrigin(origins="http://localhost:3000")
	public ResponseEntity<ApiResponse> deleteApointment(@PathVariable("apointmentId") Integer apointmentId) {
		
		this.apointmentServices.deleteApointment(apointmentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Patient Deleted Successfully", true), HttpStatus.OK) ;
		
	}
	
	
	
	//get all the apointments of a specific patient by pid
	@GetMapping("/patientApointment/{Pid}")
	@CrossOrigin(origins="http://localhost:3000")
	public List<ApointmentDetails> getApointmentsByPid(@PathVariable Integer Pid) {
		
		List<String> A_details=apointmentServices.getApointmentByPid(Pid);
        
	       List<ApointmentDetails> appointmentDetails= new ArrayList<ApointmentDetails>();
	       
	       
	       for(String s:A_details)
	       {
	           
	           String[] data= s.split(",");
	           
	           appointmentDetails.add(new ApointmentDetails(data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7]));
	       }
	        return appointmentDetails;
		
	}
	
	
	//get all the apointments of a specific doctor by did
	@GetMapping("/doctorApointment/{Did}")
	@CrossOrigin(origins="http://localhost:3000")
	public List<ApointmentDetails> getApointmentsByDid(@PathVariable Integer Did) {
		
		List<String> A_details=apointmentServices.getApointmentByDid(Did);
        
	       List<ApointmentDetails> appointmentDetails= new ArrayList<ApointmentDetails>();
	       
	       
	       for(String s:A_details)
	       {
	           
	           String[] data= s.split(",");
	           
	           appointmentDetails.add(new ApointmentDetails(data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7]));
	       }
	       
	       return appointmentDetails;
		
	}
	
}
