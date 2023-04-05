package com.appointments.pado.payloads;

import java.sql.Time;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class ApointmentDto {

	private int Aid ;
	
	private int doctorid ;
	
	private int patientid ;
	
	private Date date ;
	
	private String Slot ;
	
}
