package com.psg.ihsserver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import com.psg.ihsserver.bean.AppointmentBean;
import com.psg.ihsserver.bean.DepartmentBean;
import com.psg.ihsserver.bean.DoctorBean;
import com.psg.ihsserver.entity.Appointment;
import com.psg.ihsserver.entity.Department;
import com.psg.ihsserver.entity.Doctor;
import com.psg.ihsserver.entity.Patient;
import com.psg.ihsserver.entity.User;
import com.psg.ihsserver.oauth2.ResourceEndPoint;
import com.psg.ihsserver.oauth2.Secured;
import com.psg.ihsserver.service.AppointmentService;
import com.psg.ihsserver.service.AuthenticationService;
import com.psg.ihsserver.service.DepartmentService;
import com.psg.ihsserver.service.DoctorService;
import com.psg.ihsserver.service.PatientService;
import com.psg.ihsserver.service.UpdatesService;
import com.psg.ihsserver.util.Utils;

import oracle.net.aso.a;
import oracle.net.aso.b;


/**
 * Authorization Bearer U1NncFdkdzVTU2dwV2R3Nl9JRDpTU2dwV2R3NVNTZ3BXZHc2X1NFS1JFVA==
 * @author Dhiviya Devarajan
 *
 */
@Path("/")
public class IHSRestServer {
	
	AppointmentService appService;
	PatientService pService;
	DepartmentService deptService;
	String response;
	boolean bResponse;
	AuthenticationService authService;
	UpdatesService uService;
	DoctorService docService;
	
	@Context
	HttpServletRequest request;
	
	
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
	@Secured
	@Path("/bookAppointment")
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean bookAppointment(AppointmentBean appointment)
	{
		System.out.println("From Server, got Appointment" + appointment.getApp_date()+ " appId " + appointment.getApp_id());
		bResponse = false;
		appService=  new AppointmentService();
		bResponse = appService.bookAppointment(appointment);
		System.out.println("response is " + bResponse);
//		bResponse = true;
		return bResponse;
		//return Response.status(201).entity(bResponse).build();
//		if(bResponse)
//			response = "Appointment booked";
//		return Response.status(201).entity(response).build();
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
	public List<DepartmentBean> getAllDepartments()
	{
		deptService = new DepartmentService();
		List<DepartmentBean> departmentsList = null;
		
		departmentsList = deptService.getAllDepartments();
		System.out.println("Size of department list "+ departmentsList.size());
		
		System.out.println("Sending departmentsList data to client" + departmentsList.size());
		return departmentsList;
	}
	
	@GET
	
	@Path("/getAllDoctors")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DoctorBean> getAllDoctors()
	{
		docService = new DoctorService();
		List<DoctorBean> docs = new ArrayList<>();
		docs = docService.getAllDoctors();
		System.out.println("Sending " + docs.size() + " to app ");
		return docs;
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
	@Path("/forgotPwdOP")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean forgotPwd(@QueryParam("opCode") String opCode)
	{
		pService = new PatientService();
		
		return pService.forgotPwd(opCode);
	}
	@GET
	@Path("/forgotPwd")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean forgotPwd(@QueryParam("patient_name") String patient_name, @QueryParam("dob") String dob, @QueryParam("mobile_no") String mobile_no)
	{
		pService = new PatientService();
		String opcode = pService.forgotOpCode(patient_name, dob, mobile_no);
		if(null != opcode)
			return true;
		else
			return false;
	}
	@GET
	@Secured
	@Path("/isPatient")
	@Produces(MediaType.APPLICATION_JSON)
	public String isPatient(@QueryParam("op_code") String op_code)
	{
		
		ResourceEndPoint rp = new ResourceEndPoint();
		try {
		   Response response = rp.validateToken(request);
		} catch (OAuthSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pService = new PatientService();
		
		System.out.println("Sending Patient data to client");
		return pService.isPatient(op_code);
	}

	@POST
	@Secured
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Patient login(@FormParam("op_code") String opCode,
            @FormParam("password") String password)
	{
		System.out.println(opCode+password);
		pService = new PatientService();
		Patient patientLogin = pService.login(opCode, password);
		
		System.out.println("opCode  " +opCode);
		return patientLogin;
		
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
	
	@GET
	@Secured
	@Path("/deptDocToBeUpdated")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deptDocSync(@QueryParam(value = "lastUpdated") String lastUpdated)
	{
		System.out.println("lastUpdated " + lastUpdated);
		uService = new UpdatesService();
		boolean bool = uService.checkLastUpdated(lastUpdated);
		System.out.println( "deptDocSync "+ bool);
		return bool;
	}
	
	@POST
	@Secured
	@Path("/updateNewP")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean updateNewP(@FormParam("op_code") String opCode,
            @FormParam("password") String password)
	{
		System.out.println( "opCode+password " + opCode+ "  "+password);
		pService = new PatientService();
		return pService.updateNewP(opCode, password);
	}
	
	@GET
	@Secured
	@Path("/docForDept")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DoctorBean> docForDept(@QueryParam(value = "deptName") String deptName)
	{
		docService = new DoctorService();
		return docService.getDoctorForDepartment(deptName);
	}
	
	@GET
	@Secured
	@Path("/txId")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTxId(@QueryParam(value="opCode") String opCode, @QueryParam(value="tx_date") String tx_date)
	{
		//TODO get user transaction id for payment transaction - 
		//create transaction record in DB
		
		appService = new AppointmentService();
		 String tx_id = Utils.generateTxId(opCode, tx_date);
		 System.out.println("tx_id " + tx_id);
		boolean response = appService.updateTxId(opCode, tx_date,tx_id);
		System.out.println("tx_id update in db " +response);
		return tx_id;
	}
	
	
}
