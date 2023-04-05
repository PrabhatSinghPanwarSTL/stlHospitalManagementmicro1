package com.appointments.pado.entities;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table( name = "apointment" )
public class Apointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Aid ;
	
	private int doctorid ;
	
	private int patientid ;
	
	private Date date ;
	
	private String Slot ;

	@Override
	public String toString() {
		return "Apointment [Aid=" + Aid + ", doctorid=" + doctorid + ", patientid=" + patientid + ", date=" + date
				+ ", Slot=" + Slot + "]";
	}
	
	
	
}
