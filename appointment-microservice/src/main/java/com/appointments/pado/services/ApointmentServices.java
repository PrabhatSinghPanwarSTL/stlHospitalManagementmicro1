package com.appointments.pado.services;

import java.util.Date;
import java.util.List;

import com.appointments.pado.entities.Apointment;
import com.appointments.pado.payloads.ApointmentDto;

public interface ApointmentServices {

	ApointmentDto createApointment(ApointmentDto apointmentDto) ;
	
	ApointmentDto getApointmentByAid(Integer apointmentId) ;
	
	List<ApointmentDto> getAllApoitments() ;
	
	void deleteApointment(Integer Pid) ;
	
	ApointmentDto updateApointment(ApointmentDto apointmentDto, Integer apointmentId) ;
	
	List<String> getApointmentByPid(Integer p_id) ;
	List<String> getApointmentByDid(Integer d_id) ;
	
	List<Apointment> presentCheck(int doctorid, String slot, Date date) ;
	
}
