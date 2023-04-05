package com.doctor.appointment.services.impl;

import java.util.List; 
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctor.appointment.config.AppConstants;
import com.doctor.appointment.entities.Doctor;
//import com.doctor.appointment.entities.Role;
import com.doctor.appointment.exceptions.ResourceNotFoundException;
import com.doctor.appointment.payloads.DoctorDto;
import com.doctor.appointment.repositories.DoctorRepo;
//import com.doctor.appointment.repositories.RoleRepo;
import com.doctor.appointment.services.DoctorService;



@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private ModelMapper modelMapper ;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder ;
	
	@Autowired
	private DoctorRepo doctorRepo ;
	
//	@Autowired
//	private RoleRepo roleRepo ;
	
	
	@Override
	public DoctorDto createDoctor(DoctorDto doctorDto) {
		// TODO Auto-generated method stub
		
		Doctor doctor = this.dtoToDoctor(doctorDto) ;
		doctor.setDpassword(this.passwordEncoder.encode(doctor.getDpassword()));
		Doctor savedDoctor = this.doctorRepo.save(doctor) ;
		return this.doctorToDto(savedDoctor);
		
	}

	@Override
	public DoctorDto updateDoctor(DoctorDto doctorDto, Integer doctorId) {
		// TODO Auto-generated method stub
		
		Doctor doctor = this.doctorRepo.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctorId)) ;
		
		doctor.setDname(doctorDto.getDname());
		doctor.setEmail(doctorDto.getEmail());
		doctor.setDpassword(doctorDto.getDpassword());
		doctor.setDmobileno(doctorDto.getDmobileno());
		doctor.setDspeciality(doctorDto.getDspeciality());
		
		Doctor updatedDoctor = this.doctorRepo.save(doctor) ;
		DoctorDto doctorDto2 = this.doctorToDto(updatedDoctor) ;
		
		return doctorDto2;
	}

	@Override
	public DoctorDto getDoctorById(Integer doctorId) {
		// TODO Auto-generated method stub
		
		Doctor doctor = this.doctorRepo.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctorId)) ;
		
		return this.doctorToDto(doctor);
	}

	@Override
	public List<DoctorDto> getAllDoctor() {
		// TODO Auto-generated method stub
		
		List<Doctor> doctors = this.doctorRepo.findAll() ;
		
		List<DoctorDto> doctorDtos = doctors.stream().map(doctor -> this.doctorToDto(doctor)).collect(Collectors.toList()) ;
		
		return doctorDtos;
	}

	@Override
	public void deleteDoctor(Integer doctorId) {
		// TODO Auto-generated method stub
		
		Doctor doctor = this.doctorRepo.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctorId)) ;
		
		this.doctorRepo.delete(doctor);

	}
	
	
	
	
	public Doctor dtoToDoctor(DoctorDto doctorDto) {
		
		Doctor doctor = this.modelMapper.map(doctorDto, Doctor.class) ;
		return doctor ;
		
	}
	
	public DoctorDto doctorToDto(Doctor doctor) {
		
		DoctorDto doctorDto = this.modelMapper.map(doctor, DoctorDto.class) ;
		return doctorDto ;
		
	}

	@Override
	public DoctorDto registerNewDoctor(DoctorDto doctorDto) {
		// TODO Auto-generated method stub
		
		Doctor doctor = this.modelMapper.map(doctorDto, Doctor.class) ;
		
		//endcoded the password
		doctor.setDpassword(this.passwordEncoder.encode(doctor.getDpassword()));
		 
		//roles
//		Role role = this.roleRepo.findById(AppConstants.Role_Doctor).get() ;
//		
//		doctor.getRoles().add(role) ;
		
		Doctor newDoctor = this.doctorRepo.save(doctor) ;
		
		return this.modelMapper.map(newDoctor, DoctorDto.class) ;
	}
	

}
