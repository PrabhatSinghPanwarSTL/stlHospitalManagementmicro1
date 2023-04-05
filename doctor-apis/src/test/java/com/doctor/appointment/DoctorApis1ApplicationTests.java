package com.doctor.appointment;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.* ;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SuppressWarnings("unused")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DoctorApis1ApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Test
	@Order(1)
	public void testSignUp() {
		String jsonbody = "{\"dname\":\"Sample\",\"email\" : \"sample@gmail.com\",\"dpassword\":\"sample123\",\"dmobileno\":\"1234567890\",\"dspeciality\":\"sample\"}" ;
		String res = given()
				.header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(jsonbody)
				.when()
				.post("http://localhost:9091/api/v1/auth/register")
				.then()
				.assertThat().statusCode(201)
				.extract().response().asString() ;
	}
	
	
	@Test
	@Order(2)
	public void testLogin() {
		
		String jsonbody = "{\"username\" : \"singh@gmail.com\",\"password\" : \"123456\"}" ;
		String token = given()
				.header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(jsonbody)
				.when()
				.post("http://localhost:9091/api/v1/auth/login")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString() ;			
	}
	
	
	
	@Test
	@Order(3)
	public void testGetPatients() {
		
		String jsonbody = "{\"username\" : \"singh@gmail.com\",\"password\" : \"123456\"}" ;
		String token = given()
				.header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(jsonbody)
				.when()
				.post("http://localhost:9091/api/v1/auth/login")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString() ;	
		
		String result = given()
				.header("Authorization", "Bearer" + token).contentType(ContentType.JSON).accept(ContentType.JSON)
				.when()
				.get("http://localhost:9091/api/doctors/")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString() ;
		
	}
	
	
	@Test
	@Order(4)
	public void testGetSinghPatient() {
		
		String jsonbody = "{\"username\" : \"singh@gmail.com\",\"password\" : \"123456\"}" ;
		String token = given()
				.header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(jsonbody)
				.when()
				.post("http://localhost:9091/api/v1/auth/login")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString() ;	
		
		String result = given()
				.header("Authorization", "Bearer" + token).contentType(ContentType.JSON).accept(ContentType.JSON)
				.when()
				.get("http://localhost:9091/api/doctors/1")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString() ;
		
	}
	
	
}
