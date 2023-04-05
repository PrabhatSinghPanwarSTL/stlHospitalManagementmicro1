package com.appointments.pado;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.* ;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.http.ContentType;

@SuppressWarnings("unused")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppointmentMicroserviceApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Test
	@Order(1)
	public void testBookApo() {
		String jsonbody = "{\"doctorid\":\"1\",\"patientid\" : \"1\",\"date\":\"2023-03-01\",\"slot\":\"10:00am - 10:30am\"}" ;
		
		String res = given()
				.header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(jsonbody)
				.when()
				.post("http://localhost:2023/api/apointments/")
				.then()
				.assertThat().statusCode(201)
				.extract().response().asString() ;
	}
	
	
	
	@Test
	@Order(2)
	public void testGetAllApo() {
		
		String res = given()
				.when()
				.get("http://localhost:2023/api/apointments/")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString() ;
		
	}
	
	
	@Test
	@Order(3)
	public void testGetAllPaiApo() {
		
		String res = given()
				.when()
				.get("http://localhost:2023/api/apointments/patientApointment/1")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString() ;
		
	}
	
	
	@Test
	@Order(4)
	public void testGetAllDocApo() {
		
		String res = given()
				.when()
				.get("http://localhost:2023/api/apointments/doctorApointment/1")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString() ;
		
	}
	
	
	
	@Test
	@Order(5)
	public void testDeleteApo() {
		
		String res = given()
				.when()
				.delete("http://localhost:2023/api/apointments/63")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString() ;
		
	}
	
	
	@Test
	@Order(7)
	public void testUpdateApo() {
		
		String jsonbody = "{\"doctorid\":\"4\",\"patientid\" : \"1\",\"date\":\"2023-03-01\",\"slot\":\"10:00am - 10:30am\"}" ;
		
		String res = given()
				.header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(jsonbody)
				.when()
				.put("http://localhost:2023/api/apointments/5")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString() ;
				
		
	}
	
	
}
