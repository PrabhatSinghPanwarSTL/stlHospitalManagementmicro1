package com.appointments.pado.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointments.pado.Exceptions.ResourceNotFoundException;
import com.appointments.pado.entities.Apointment;
import com.appointments.pado.entities.Patient;
import com.appointments.pado.payloads.ApointmentDto;
import com.appointments.pado.repositories.ApointmentRepo;
import com.appointments.pado.services.ApointmentServices;

@Service
public class ApointmentServiceImpl implements ApointmentServices {

	@Autowired
	private ModelMapper modelMapper ;
	
	@Autowired
	private ApointmentRepo apointmentRepo ;
	
	
	@Override
	public ApointmentDto createApointment(ApointmentDto apointmentDto) {
		// TODO Auto-generated method stub
		
		Apointment apointment = this.dtoToApointment(apointmentDto) ;
		Apointment savedApointment = this.apointmentRepo.save(apointment) ;
		
		return this.apointmentToDto(savedApointment);
	}

	
	@Override
	public List<ApointmentDto> getAllApoitments() {
		// TODO Auto-generated method stub
		
		List<Apointment> apointments = this.apointmentRepo.findAll() ;
		
		List<ApointmentDto> apointmentDtos = apointments.stream().map(apointment -> this.apointmentToDto(apointment)).collect(Collectors.toList()) ;
		
		return apointmentDtos ;
	}
	
	
	
	public Apointment dtoToApointment(ApointmentDto apointmentDto) {
		
		Apointment apointment = this.modelMapper.map(apointmentDto, Apointment.class) ;
		return apointment ;
		
	}
	
	public ApointmentDto apointmentToDto(Apointment apointment) {
		
		ApointmentDto apointmentDto = this.modelMapper.map(apointment, ApointmentDto.class) ;
		return apointmentDto ;
		
	}


	@Override
	public void deleteApointment(Integer apointmentId) {
		// TODO Auto-generated method stub
		
		Apointment apointment = this.apointmentRepo.findById(apointmentId).orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", apointmentId)) ;
		
		this.apointmentRepo.delete(apointment);
				
	}

	
	@Override
	public List<String> getApointmentByPid(Integer p_id) {
		// TODO Auto-generated method stub
		
		return  this.apointmentRepo.findApointmentByPatientid(p_id);
	}


	@Override
	public List<String> getApointmentByDid(Integer d_id) {
		// TODO Auto-generated method stub
		
		return this.apointmentRepo.findApointmentByDoctorid(d_id);
	}


	@Override
	public ApointmentDto updateApointment(ApointmentDto apointmentDto, Integer apointmentId) {
		// TODO Auto-generated method stub
		
		Apointment apointment = this.apointmentRepo.findById(apointmentId).orElseThrow(() -> new ResourceNotFoundException("Apointment", "Aid", apointmentId)) ;
		
		apointment.setDoctorid(apointmentDto.getDoctorid());
		apointment.setDate(apointmentDto.getDate());
		apointment.setPatientid(apointmentDto.getPatientid());
		apointment.setSlot(apointmentDto.getSlot());
//		apointment.setDoctorname(apointmentDto.getDoctorname());
//		apointment.setPatientname(apointmentDto.getPatientname());
		
		Apointment updatedapointment = this.apointmentRepo.save(apointment) ;
		ApointmentDto apointmentDto1 = this.apointmentToDto(updatedapointment) ;
		
		return apointmentDto1 ;
	}


	@Override
	public ApointmentDto getApointmentByAid(Integer apointmentId) {
		// TODO Auto-generated method stub
		
		Apointment apointment = this.apointmentRepo.findById(apointmentId).orElseThrow(() -> new ResourceNotFoundException("Appointment", "Aid", apointmentId)) ;
		
		return this.apointmentToDto(apointment);
	}


	@Override
	public List<Apointment> presentCheck(int doctorid, String slot, Date date) {

		List<Apointment> value = this.apointmentRepo.findApointmentByslot(doctorid, slot, date) ;
		
		return value;
	}

	
	
}
