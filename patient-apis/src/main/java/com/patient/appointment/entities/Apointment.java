package com.patient.appointment.entities;

import java.sql.Time;
import java.util.Date;

import lombok.Data;


@Data
public class Apointment {

	private int Aid ;
	
	private int doctorid ;
	
	private int patientid ;
	
	private Date date ;
	
	private String Slot ;
	
	private String doctorname ;
	
}
