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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import com.psg.ihsserver.bean.AppointmentBean;
import com.psg.ihsserver.bean.DepartmentBean;
import com.psg.ihsserver.bean.DoctorBean;
import com.psg.ihsserver.bean.PatientBean;
import com.psg.ihsserver.daoimpl.PatientDaoImpl;
import com.psg.ihsserver.entity.Appointment;
import com.psg.ihsserver.entity.Patient;
import com.psg.ihsserver.entity.User;
import com.psg.ihsserver.exception.ApplicationException;
import com.psg.ihsserver.oauth2.ResourceEndPoint;
import com.psg.ihsserver.oauth2.Secured;
import com.psg.ihsserver.service.AppointmentService;
import com.psg.ihsserver.service.AuthenticationService;
import com.psg.ihsserver.service.DepartmentService;
import com.psg.ihsserver.service.DoctorService;
import com.psg.ihsserver.service.PatientService;
import com.psg.ihsserver.service.UpdatesService;
import com.psg.ihsserver.servicejdbc.AppointmentServiceJdbc;
import com.psg.ihsserver.servicejdbc.DepartmentServiceJdbc;
import com.psg.ihsserver.servicejdbc.PatientServiceJdbc;
import com.psg.ihsserver.util.Utils;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;

/**
 * Authorization Bearer
 * U1NncFdkdzVTU2dwV2R3Nl9JRDpTU2dwV2R3NVNTZ3BXZHc2X1NFS1JFVA==
 * 
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

	CacheManager cm = CacheManager.getInstance();
	Cache cache = cm.getCache(BackgroundJobManager.cacheName);

	private static final Logger logger = Logger.getLogger(IHSRestServer.class);

	@Context
	HttpServletRequest request;

	@GET
	@Produces("text/html")
	public Response getStartingPage() {
		String output = "<h1>IHSServer!<h1>" + "<p>IHSServer is running ... <br>Ping @ " + new Date().toString()
				+ "</p<br>";
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/getSampleUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User getSampleUser() {
		User user = new User();
		user.setFirstName("Subbu");
		user.setLastName("Samprathi");
		user.setAadharNo("somebs");
		user.setPhoneNo("somecrap");

		logger.info("Sending sample User data to client");
		return user;
	}

	@POST
	@Path("/insertNewPatient")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putPatientData(Patient patient) {
		try {
			String actualPassword = patient.getPatient_pwd();
			String encPassword = Utils.encrypt(actualPassword);
			patient.setPatient_pwd(encPassword);
			pService = new PatientService();
			boolean booleanResponse = pService.insertNewPatient(patient);
			if (booleanResponse == false)
				response = "Patient Saved";
			logger.info("Inserting New Patient: " + response);
			return Response.status(201).entity(response).build();
		} catch (NullPointerException n) {
			logger.error(" " + n.getMessage());
			return Response.status(417).entity("Insufficient data").build();
		} catch (ApplicationException e) {
			// TODO: handle exception
			return Response.status(400).entity("Cannot insert Patient").build();
		}
	}

	@POST
	@Path("/insertNewUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putUserData(User user) {
		System.out.println("From Server1 ");
		System.out.println("From Server " + user.getFirstName());
		String response = "User saved";
		logger.info(response);
		return Response.status(201).entity(response).build();
	}

	@GET
	@Path("/getPatient")
	@Produces(MediaType.APPLICATION_JSON)
	public Patient getPatient() {
		logger.setLevel(Level.ALL);
		logger.info("sample msg");
		System.out.println("Sending sample Patient data to client");
		// return p;
		return null;
	}

	@GET
	@Path("/getPatientByOnlineRegNo")
	@Produces(MediaType.APPLICATION_JSON)
	public Patient getPatientByOnlineRegNo(@QueryParam("online_reg_no") String online_reg_no) {
		try {
			pService = new PatientService();
			Patient patient = pService.getPatient(online_reg_no);
			logger.info("Sending Patient data to client" + patient.getPatient_name());
			return patient;
		} catch (ApplicationException e) {
			logger.info(e.getMessage());
			return null;
		} catch (NullPointerException n) {
			logger.error(" " + n.getMessage());
			return null;
		}
		// TODO: handle exception

	}

	@GET
	@Path("/getPatientByOpCode")
	@Produces(MediaType.APPLICATION_JSON)
	public Patient getPatientByOpCode(@QueryParam("op_code") String op_code) {
		Patient patient = null;
		try {
			pService = new PatientService();
			patient = pService.getPatientByOpCode(op_code);
			logger.info("Sending Patient data to client");
		} catch (ApplicationException e) {
			logger.info(e.getMessage());
		} catch (NullPointerException n) {
			logger.error(" " + n.getMessage());
		}
		return patient;

	}

	@POST
	@Path("/updatePatient")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePatientData(Patient patient) {
		try {
			String pwd = patient.getPatient_pwd();
			String encPassword = Utils.encrypt(pwd);
			patient.setPatient_pwd(encPassword);
			pService = new PatientService();
			bResponse = pService.updatePatient(patient);
			if (!bResponse)
				response = "Patient updated";
			logger.info(response);
			return Response.status(201).entity(response).build();
		} catch (ApplicationException e) {
			logger.info(e.getMessage());
			return Response.status(204).entity("Patient Cannot be updated").build();
		} catch (NullPointerException n) {
			logger.error(" " + n.getMessage());
			return Response.status(400).entity("Client data null").build();
		}
	}

	@POST
	@Secured
	@Path("/bookAppointment")
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean bookAppointment(AppointmentBean appointment) {
		System.out.println(
				"From Server, got Appointment" + appointment.getApp_date() + " appId " + appointment.getApp_id());
		bResponse = false;
		appService = new AppointmentService();
		try {
			bResponse = appService.bookAppointment(appointment);

			logger.info("Appointment booking status: " + bResponse);
			// bResponse = true;
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		return bResponse;
		// return Response.status(201).entity(bResponse).build();
		// if(bResponse)
		// response = "Appointment booked";
		// return Response.status(201).entity(response).build();
	}

	@POST
	@Path("/cancelAppointment")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelAppointment(@QueryParam("online_reg_no") String online_reg_no,
			@QueryParam("app_date") Date app_date) {
		System.out.println("From Server, got Patient" + app_date);
		java.sql.Date sqlDate = null;
		appService = new AppointmentService();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date;
		try {

			date = sdf1.parse(app_date.toString());
			sqlDate = new java.sql.Date(date.getTime());

			bResponse = appService.cancelAppointment(online_reg_no, sqlDate);
			if (bResponse)
				response = "Appointment cancelled";
			logger.info(response);
			return Response.status(201).entity(response).build();
		} catch (ParseException e) {
			logger.info(e.getMessage());
			return Response.status(400).entity("Bad date format").build();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			return Response.status(409).entity("Cannot cancel appointment").build();
		} catch (NullPointerException e) {
			logger.info(" " + e.getMessage());
			return Response.status(400).entity("Null data").build();
		}
	}

	@GET
	@Path("/getAllAppointments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Appointment> getAllAppointmentsForPatient(@QueryParam("online_reg_no") String online_reg_no) {
		appService = new AppointmentService();
		List<Appointment> appointmentsList = null;

		try {
			appointmentsList = appService.getAllAppointmentsForPatient(online_reg_no);

			logger.info("Sending Appointment data to client" + appointmentsList.size());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		return appointmentsList;
	}

	@GET
	@Path("/getAllDepartments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DepartmentBean> getAllDepartments() {
		deptService = new DepartmentService();
		List<DepartmentBean> departmentsList = null;

		try {
			departmentsList = deptService.getAllDepartments();
			logger.info("Sending departmentsList data to client" + departmentsList.size());
		} catch (ApplicationException e) {
			// TODO: handle exception
			logger.info(e.getMessage());
		}
		return departmentsList;
	}

	@GET

	@Path("/getAllDoctors")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DoctorBean> getAllDoctors() {
		docService = new DoctorService();
		List<DoctorBean> docs = new ArrayList<>();
		try {
			docs = docService.getAllDoctors();
			logger.info("Sending doctors list" + docs.size() + " to app ");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}

		return docs;
	}

	@GET
	@Path("/getPatientByDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Patient getPatientByDetails(@QueryParam("patient_name") String patient_name, @QueryParam("dob") String dob,
			@QueryParam("mobile_no") String mobile_no) {
		try {
			pService = new PatientService();
			Patient pByDetails = pService.getPatientByDetails(patient_name, dob, mobile_no);
			logger.info("dob in time " + pByDetails.getDob().getTime());
			return pByDetails;
		} catch (ApplicationException e) {
			// TODO: handle exception
			return null;
		}
	}

	/**
	 * Tried with BeanParam, but doesnt work for Date. Need to create a custom
	 * DateParam type for this - Ref-
	 * https://softwareengineering.stackexchange.com/questions/138391/should-i-use-the-date-type-in-jax-rs-pathparam
	 */

	@GET
	@Path("/forgotOpCode")
	@Produces(MediaType.APPLICATION_JSON)
	public String forgotOpCode(@QueryParam("patient_name") String patient_name, @QueryParam("dob") String dob,
			@QueryParam("mobile_no") String mobile_no) {
		try {
			pService = new PatientService();
			String opcode = pService.forgotOpCode(patient_name, dob, mobile_no);
			System.out.println("dob in time " + opcode);
			return opcode;
		} catch (ApplicationException e) {
			// TODO: handle exception
			return null;
		}
	}

	@GET
	@Path("/forgotPwd")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean forgotPwd(@QueryParam("opCode") String opCode) {
		try {
			pService = new PatientService();

			return pService.forgotPwd(opCode);
		} catch (ApplicationException e) {
			// TODO: handle exception
			return false;
		}
	}

	@GET
	@Path("/forgotOP")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean forgotPwd(@QueryParam("patient_name") String patient_name, @QueryParam("dob") String dob,
			@QueryParam("mobile_no") String mobile_no) {
		try {
			pService = new PatientService();
			String opcode = pService.forgotOpCode(patient_name, dob, mobile_no);
			if (null != opcode)
				return true;
			else
				return false;
		} catch (ApplicationException e) {
			// TODO: handle exception
			return false;

		}
	}

	@GET
	@Secured
	@Path("/isPatient")
	@Produces(MediaType.APPLICATION_JSON)
	public String isPatient(@QueryParam("op_code") String op_code) {
		try {
			ResourceEndPoint rp = new ResourceEndPoint();

			Response response = rp.validateToken(request);

			pService = new PatientService();

			return pService.isPatient(op_code);
		} catch (OAuthSystemException e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			return null;
		} catch (ApplicationException e) {
			// TODO: handle exception
			return null;
		}
	}

	@POST
	@Secured
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Patient login(@FormParam("op_code") String opCode, @FormParam("password") String password) {
		try {
			pService = new PatientService();
			String encPassword = Utils.encrypt(password);
			Patient patientLogin = pService.login(opCode, encPassword);
			logger.info("patient login :" + patientLogin);
			return patientLogin;
		} catch (ApplicationException e) {
			return null;
		}
	}

	@POST
	@Secured
	@Path("/getpwd/{op_code}")
	@Produces(MediaType.APPLICATION_JSON)
	public String decryptpassword(@PathParam("op_code") String opCode) {
		try {
			PatientDaoImpl pd = new PatientDaoImpl();
			String pwd = pd.getPassword(opCode);
			return pwd;
		} catch (ApplicationException e) {
			// TODO: handle exception
			return null;
		}
	}

	@GET
	@Path("/getAuthToken")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAuthToken() {
		String authToken = null;
		return authService.getAuthToken();
	}

	@GET
	@Path("/patientSignIn")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean patientSignIn() {
		return false;
	}

	@GET
	@Secured
	@Path("/deptDocToBeUpdated")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deptDocSync(@QueryParam(value = "lastUpdated") String lastUpdated) {
		logger.info("lastUpdated " + lastUpdated);
		uService = new UpdatesService();
		boolean bool = uService.checkLastUpdated(lastUpdated);
		logger.info("deptDocSync " + bool);
		return bool;
	}

	@POST
	@Secured
	@Path("/updateNewP")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean updateNewP(@FormParam("op_code") String opCode, @FormParam("password") String password) {
		try {
			System.out.println("opCode+password " + opCode + "  " + password);
			pService = new PatientService();
			return pService.updateNewP(opCode, password);
		} catch (ApplicationException e) {
			// TODO: handle exception
			return false;
		} catch (NullPointerException e) {
			logger.info(" " + e.getMessage());
			return false;
		}
	}

	@GET
	@Secured
	@Path("/docForDept")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DoctorBean> docForDept(@QueryParam(value = "deptName") String deptName) {
		docService = new DoctorService();
		try {
			return docService.getDoctorForDepartment(deptName);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return null;
		}
	}

	@GET
	@Secured
	@Path("/txId")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTxId(@QueryParam(value = "opCode") String opCode, @QueryParam(value = "tx_date") String tx_date) {
		String tx_id = null;
		try {

			// create transaction record in DB
			appService = new AppointmentService();
			tx_id = Utils.generateTxId(opCode, tx_date);
			logger.info("transaction id generated " + tx_id);
			boolean response = appService.updateTxId(opCode, tx_date, tx_id);
			logger.info("tx_id update in db " + response);

		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		return tx_id;
	}

	@POST
	@Secured
	@Path("/sendOTP")
	public String sendOTPehcache(@QueryParam(value = "opCode") String opCode) {

		try {
			String otp = null;
			cache.evictExpiredElements();
			boolean checkValue = cache.isKeyInCache(opCode);
			if (checkValue == false) {
				otp = Utils.generateOTP(opCode);
				Element e = new Element(opCode, otp);
				cache.put(e);
				logger.info("otp generated: " + otp);
				return otp;
			} else {
				Element ele = cache.get(opCode);
				otp = (ele == null ? null : ele.getObjectValue().toString());
				System.out.println("otp resent : " + otp);

				return otp;
			}
		} catch (ApplicationException e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return null;
		}
	}

	@POST
	@Secured
	@Path("/verifyOTP")
	public boolean verifyOTPehcache(@QueryParam(value = "opCode") String opCode,
			@QueryParam(value = "otp") String otpToVerify) {

		try {
			cache.evictExpiredElements();
			boolean checkValue = cache.isKeyInCache(opCode);
			if (checkValue == false) {
				logger.info("No otp generated for opcode");
				return false;
			}
			Element element = cache.get(opCode);
			String otp = element.getObjectValue().toString();

			if (otpToVerify.equals(otp)) {
				logger.info("otp verified");
				return true;
			} else {
				logger.info("OTP does not match");
				return false;
			}
		} catch (CacheException e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return false;
		}
	}

	@GET
	@Path("/forgotOpCodeProc")
	@Produces(MediaType.APPLICATION_JSON)
	public String forgotOpCodeProc(@QueryParam("patient_name") String patientName, @QueryParam("dob") String dob,
			@QueryParam("mobile_no") String mobileNo) {
		try {
			System.out.println("data: " + patientName + " " + dob + " " + mobileNo);
			PatientServiceJdbc patientService = new PatientServiceJdbc();
			String opcode = patientService.forgotOpCode(patientName, dob, mobileNo);
			return opcode;
		} catch (ApplicationException e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return null;
		}
	}

	@GET
	@Path("/forgotPwdProc")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean forgotPwdProc(@QueryParam("patient_name") String patientName, @QueryParam("dob") String dob,
			@QueryParam("mobile_no") String mobileNo) {
		try {
			PatientServiceJdbc patientService = new PatientServiceJdbc();
			System.out.println("data: " + patientName + " " + dob + " " + mobileNo);
			String opcode = patientService.forgotOpCode(patientName, dob, mobileNo);
			if (null != opcode)
				return true;
			else
				return false;
		} catch (ApplicationException e) {
			// TODO: handle exception
			return false;

		}
	}

	@GET
	@Secured
	@Path("/isPatientProc")
	@Produces(MediaType.APPLICATION_JSON)
	public String isPatientProc(@QueryParam("op_code") String opCode) {
		try {
			PatientServiceJdbc patientService = new PatientServiceJdbc();
			String mobile = patientService.isPatient(opCode);
			return mobile;
		} catch (ApplicationException e) {
			// TODO: handle exception
			return null;
		}
	}

	@POST
	@Path("/updatePatientProc")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePatientDataProc(PatientBean patient) {
		try {
			PatientServiceJdbc patientService = new PatientServiceJdbc();
			bResponse = patientService.updatePatient(patient);
			if (bResponse)
				response = "Patient updated";
			logger.info(response);
			return Response.status(201).entity(response).build();
		} catch (ApplicationException e) {
			logger.info(e.getMessage());
			return Response.status(204).entity("Patient Cannot be updated").build();
		}
	}

	@POST
	@Secured
	@Path("/loginProc")
	@Produces(MediaType.APPLICATION_JSON)
	public PatientBean loginProc(@FormParam("op_code") String opCode, @FormParam("password") String password) {
		try {
			String encPassword = Utils.encrypt(password);
			PatientServiceJdbc patientService = new PatientServiceJdbc();
			Patient patientLogin = patientService.login(opCode, encPassword);
			if (patientLogin != null) {
				PatientBean patientBean = Utils.convertToBean(patientLogin);
				return patientBean;
			} else
				return null;
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@GET
	@Path("/getAllDepartmentsProc")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DepartmentBean> getAllDepartmentsProc() {
		deptService = new DepartmentService();
		List<DepartmentBean> departmentsList = null;

		try {
			DepartmentServiceJdbc deptService = new DepartmentServiceJdbc();
			departmentsList = deptService.getAllDepartments();
		} catch (ApplicationException e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return departmentsList;
	}

	/*
	 * Procedure call for doctor get by dept no not be implemented
	 * 
	 * @GET
	 * 
	 * @Path("/getAllDoctorsProc")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public List<DoctorBean>
	 * getAllDoctorsProc(@QueryParam("deptno") Integer deptNo ) {
	 * List<DoctorBean> docs = null; try { DoctorServiceJdbc doctorService= new
	 * DoctorServiceJdbc(); docs=doctorService.getDoctorForDepartment(deptNo); }
	 * catch (ApplicationException e) { logger.error(e.getMessage()); return
	 * null; }
	 * 
	 * return docs; }
	 */

	@POST
	@Secured
	@Path("/bookAppointmentProc")
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean bookAppointmentProc(AppointmentBean appointment) {
		bResponse = false;
		System.out.println("appointment " + appointment);
		try {
			AppointmentServiceJdbc appointmentService = new AppointmentServiceJdbc();
			bResponse = appointmentService.bookAppointment(appointment);
			logger.info("Appointment booking status: " + bResponse);
		} catch (ApplicationException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error("NullPointerException " + e.getMessage());
		}
		return bResponse;
	}

	@GET
	@Secured
	@Path("/txIdProc")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTxIdProc(@QueryParam(value = "opCode") String opCode,
			@QueryParam(value = "tx_date") String tx_date) {
		String tx_id = null;
		try {
			AppointmentServiceJdbc appointmentService = new AppointmentServiceJdbc();
			tx_id = Utils.generateTxId(opCode, tx_date);
			boolean response = appointmentService.storeTxId(opCode, tx_date, tx_id);
			logger.info("tx_id update in db " + response);

		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			return null;
		}
		return tx_id;
	}

	@POST
	@Path("/cancelAppointmentProc")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelAppointmentProc(@QueryParam("online_reg_no") String opcode,
			@QueryParam("app_date") String app_date) {

		try {
			AppointmentServiceJdbc appointmentService = new AppointmentServiceJdbc();
			bResponse = appointmentService.cancelAppointment(opcode, app_date);
			if (bResponse)
				response = "Appointment cancelled";
			logger.info(response);
			return Response.status(201).entity(response).build();
		} catch (ApplicationException e) {
			return Response.status(409).entity("Cannot cancel appointment").build();
		} catch (NullPointerException e) {
			logger.info(" " + e.getMessage());
			return Response.status(400).entity("Null data").build();
		}
	}

	@GET
	@Path("/getAllAppointmentsProc")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AppointmentBean> getAllAppointmentsForPatientProc(@QueryParam("online_reg_no") String online_reg_no) {
		List<AppointmentBean> appointmentsList = null;
		try {
			AppointmentServiceJdbc appointmentService = new AppointmentServiceJdbc();
			appointmentsList = appointmentService.getAllAppointmentsForPatient(online_reg_no);
			logger.info("Sending Appointment data to client" + appointmentsList.size());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		return appointmentsList;
	}
}
