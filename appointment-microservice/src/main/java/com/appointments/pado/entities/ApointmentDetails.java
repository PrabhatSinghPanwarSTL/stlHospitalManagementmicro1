package com.appointments.pado.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApointmentDetails {

	private String aid ;
	private String date ;
	private String slot ;
	private String doctorname ;
	private String demail ;
	private String speciality ;
	private String patientname ;
	private String pemail ;
	
	
	
	public ApointmentDetails(String aid, String date, String slot, String doctorname, String demail, String patientname,
			String pemail, String speciality) {
		super();
		this.aid = aid;
		this.date = date;
		this.slot = slot;
		this.doctorname = doctorname;
		this.demail = demail;
		this.speciality = speciality ;
		this.patientname = patientname;
		this.pemail = pemail;
	}



	@Override
	public String toString() {
		return "ApointmentDetails [aid=" + aid + ", date=" + date + ", slot=" + slot + ", doctorname=" + doctorname
				+ ", demail=" + demail + ", speciality" + ", patientname=" + patientname + ", pemail=" + pemail + "]";
	}
	
	
	
}
