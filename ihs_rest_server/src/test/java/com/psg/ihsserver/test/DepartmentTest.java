package com.psg.ihsserver.test;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;

public class DepartmentTest {
	
	@Test
	public void getAllDepartments()
	{
		given().when().get("http://172.16.9.80:8081/ihsserver/getAllAppointments?online_reg_no=I17000001").then().statusCode(200);
	}

}
