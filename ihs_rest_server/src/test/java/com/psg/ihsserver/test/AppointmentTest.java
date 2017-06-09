package com.psg.ihsserver.test;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;

import com.psg.ihsserver.entity.Appointment;



public class AppointmentTest {
	
	Utils utilObj = new Utils();

	@Test
	public void getAllAppointments()
	{
		given().when().get("http://172.16.9.80:8081/ihsserver/getAllAppointments?online_reg_no=I17000001").then().statusCode(200);
	}
	
	@Test
	public void bookAppointment()
	{
		Appointment appointment = new Appointment();
		//TODO Date Serialization from Test Rest client
		//appointment.setApp_date(utilObj.generateSQLDate("05-06-2017"));
		appointment.setOnline_reg_no("I17000001");
		appointment.setCharge(1221);
		given()
		.contentType("application/json")
		.when()
		.body(appointment)
		.post("http://172.16.9.80:8081/ihsserver/bookAppointment").then().statusCode(201);
	}
	
	@Test
	public void cancelAppointment()
	{
		Appointment appointment = new Appointment();
		//TODO Date Serialization from Test Rest client
		//appointment.setApp_date(utilObj.generateSQLDate("05-06-2017"));
		appointment.setOnline_reg_no("I17000001");
		appointment.setCharge(1221);
		
		given()
		.contentType("application/json")
		.when()
		.body(appointment)
		.post("http://172.16.9.80:8081/ihsserver/cancelAppointment").then().statusCode(201);
	}
}

