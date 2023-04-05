package com.patient.appointment;

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
class PatientApisApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	
	@Test
	@Order(1)
	public void testSignUp() {
		String jsonbody = "{\"name\":\"Sample\",\"email\" : \"sample@gmail.com\",\"password\":\"sample123\",\"mobileno\":\"1234567890\"}" ;
		String res = given()
				.header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(jsonbody)
				.when()
				.post("http://localhost:9090/api/v2/auth/registerpatient")
				.then()
				.assertThat().statusCode(201)
				.extract().response().asString() ;
	}
	
	
	@Test
	@Order(2)
	public void testLogin() {
		
		String jsonbody = "{\"username\" : \"anupam@gmail.com\",\"password\" : \"12345\"}" ;
		String token = given()
				.header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(jsonbody)
				.when()
				.post("http://localhost:9090/api/v2/auth/login")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString() ;			
	}
	
	
	@Test
	@Order(3)
	public void testGetPatients() {
		
		String jsonbody = "{\"username\" : \"anupam@gmail.com\",\"password\" : \"12345\"}" ;
		String token = given()
				.header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(jsonbody)
				.when()
				.post("http://localhost:9090/api/v2/auth/login")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString() ;	
		
		String result = given()
				.header("Authorization", "Bearer" + token).contentType(ContentType.JSON).accept(ContentType.JSON)
				.when()
				.get("http://localhost:9090/api/users/")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString() ;
		
	}
	
	
	
	@Test
	@Order(4)
	public void testGetSinglePatient() {
		
		String jsonbody = "{\"username\" : \"anupam@gmail.com\",\"password\" : \"12345\"}" ;
		String token = given()
				.header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(jsonbody)
				.when()
				.post("http://localhost:9090/api/v2/auth/login")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString() ;	
		
		String result = given()
				.header("Authorization", "Bearer" + token).contentType(ContentType.JSON).accept(ContentType.JSON)
				.when()
				.get("http://localhost:9090/api/users/1")
				.then()
				.assertThat().statusCode(200)
				.extract().response().asString() ;
		
	}
	
}
