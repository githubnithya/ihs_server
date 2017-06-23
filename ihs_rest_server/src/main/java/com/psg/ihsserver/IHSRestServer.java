package com.psg.ihsserver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.psg.ihsserver.entity.Appointment;
import com.psg.ihsserver.entity.Department;
import com.psg.ihsserver.entity.Patient;
import com.psg.ihsserver.entity.User;
import com.psg.ihsserver.service.AppointmentService;
import com.psg.ihsserver.service.AuthenticationService;
import com.psg.ihsserver.service.DepartmentService;
import com.psg.ihsserver.service.PatientService;

@Path("/")
public class IHSRestServer {
	
	AppointmentService appService;
	PatientService pService;
	DepartmentService deptService;
	String response;
	boolean bResponse;
	AuthenticationService authService;
	
	
	@GET
	@Produces("text/html")
	public Response getStartingPage()
	{
		String output = "<h1>IHSServer!<h1>" +
				"<p>IHSServer is running ... <br>Ping @ " + new Date().toString() + "</p<br>";
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/getSampleUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User getSampleUser()
	{
		User user = new User();
		user.setFirstName("Subbu");
		user.setLastName("Samprathi");
		user.setAadharNo("somebs");
		user.setPhoneNo("somecrap");
		
		System.out.println("Sending sample User data to client");
		return user;
	}
	
	@POST
	@Path("/insertNewPatient")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putPatientData(Patient patient)
	{
		
		System.out.println("From Server, got Patient" +patient.getPatient_name());
		pService = new PatientService();
		boolean booleanResponse= pService.insertNewPatient(patient);
		
		if(booleanResponse)
			response = "Patient Saved";
		
		return Response.status(201).entity(response).build();
	}

	@POST
	@Path("/insertNewUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putUserData(User user)
	{
		System.out.println("From Server1 ");
		System.out.println("From Server " +user.getFirstName());
	
		String response = "User saved";
		return Response.status(201).entity(response).build();
	}
	
	@GET
	@Path("/getPatient")
	@Produces(MediaType.APPLICATION_JSON)
	public Patient getPatient()
	{
		
		System.out.println("Sending sample Patient data to client");
	//	return p;
		return null;
	}
	
	@GET
	@Path("/getPatientByOnlineRegNo")
	@Produces(MediaType.APPLICATION_JSON)
	public Patient getPatientByOnlineRegNo(@QueryParam("online_reg_no") String online_reg_no)
	{
		pService = new PatientService();
		Patient patient = pService.getPatient(online_reg_no);
		
		
		System.out.println("Sending Patient data to client" + patient.getPatient_name());
		return patient;
	}
	
	@GET
	@Path("/getPatientByOpCode")
	@Produces(MediaType.APPLICATION_JSON)
	public Patient getPatientByOpCode(@QueryParam("op_code") String op_code)
	{
		pService = new PatientService();
		Patient patient = pService.getPatientByOpCode(op_code);
		
		System.out.println("Sending Patient data to client");
		return patient;
	}
	
	@POST
	@Path("/updatePatient")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePatientData(Patient patient)
	{
		System.out.println("From Server, got Patient" +patient.getPatient_name());
		
		pService = new PatientService();
		bResponse = pService.updatePatient(patient);
					
		if(bResponse) 
			response = "Patient updated";
		return Response.status(201).entity(response).build();
	}
	
	@POST
	@Path("/bookAppointment")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response bookAppointment(Appointment appointment)
	{
		System.out.println("From Server, got Patient" + appointment.getApp_date());
		
		appService=  new AppointmentService();
		bResponse = appService.bookAppointment(appointment);
		if(bResponse)
			response = "Appointment booked";
		return Response.status(201).entity(response).build();
	}
	//TODO Change Date param to String
	@POST
	@Path("/cancelAppointment")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelAppointment(@QueryParam("online_reg_no") String online_reg_no, @QueryParam("app_date") Date app_date)
	{
		System.out.println("From Server, got Patient" + app_date);
		java.sql.Date sqlDate = null;
		appService = new AppointmentService();
		//TODO Check date format change
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date;
		try {
			
			date = sdf1.parse(app_date.toString());
			sqlDate = new java.sql.Date(date.getTime());  
			
			} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		bResponse = appService.cancelAppointment(online_reg_no, sqlDate);
		if(bResponse)
			response = "Appointment cancelled";
		return Response.status(201).entity(response).build();
	}
	
	@GET
	@Path("/getAllAppointments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Appointment> getAllAppointmentsForPatient(@QueryParam("online_reg_no") String online_reg_no)
	{
		appService = new AppointmentService();
		List<Appointment> appointmentsList = null;
		
		appointmentsList = appService.getAllAppointmentsForPatient(online_reg_no);
		
		System.out.println("Sending Appointment data to client" + appointmentsList.size());
		return appointmentsList;
	}
	
	@GET
	@Path("/getAllDepartments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Department> getAllDepartments()
	{
		deptService = new DepartmentService();
		List<Department> departmentsList = null;
		
		departmentsList = deptService.getAllDepartments();
		System.out.println("Size of department list "+ departmentsList.size());
		
		System.out.println("Sending departmentsList data to client" + departmentsList.size());
		return departmentsList;
	}
	
	
	@GET
	@Path("/getPatientByDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Patient getPatientByDetails(@QueryParam("patient_name") String patient_name, @QueryParam("dob") String dob, @QueryParam("mobile_no") String mobile_no)
	{
		pService = new PatientService();
		Patient pByDetails = pService.getPatientByDetails(patient_name, dob, mobile_no);
		
		System.out.println("dob in time " +pByDetails.getDob().getTime());
		return pByDetails;
	}
	/**
	 * Tried with BeanParam, but doesnt work for Date.
	 * Need to create a custom DateParam type for this - Ref- https://softwareengineering.stackexchange.com/questions/138391/should-i-use-the-date-type-in-jax-rs-pathparam
	 */
	
	
	@GET
	@Path("/forgotOpCode")
	@Produces(MediaType.APPLICATION_JSON)
	public String forgotOpCode(@QueryParam("patient_name") String patient_name, @QueryParam("dob") String dob, @QueryParam("mobile_no") String mobile_no)
	{
		pService = new PatientService();
		String opcode = pService.forgotOpCode(patient_name, dob, mobile_no);
		
		System.out.println("dob in time " +opcode);
		return opcode;
	}
	
	@GET
	@Path("/isPatient")
	@Produces(MediaType.APPLICATION_JSON)
	public String isPatient(@QueryParam("op_code") String op_code)
	{
		pService = new PatientService();
		
		
		System.out.println("Sending Patient data to client");
		return pService.isPatient(op_code);
	}

	@GET
	@Path("/getAuthToken")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAuthToken()
	{
		String authToken = null;
		
		
		
		return authService.getAuthToken();
	}
	
	
	@GET
	@Path("/patientSignIn")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean patientSignIn()
	{
		
		return false;
	}
}
