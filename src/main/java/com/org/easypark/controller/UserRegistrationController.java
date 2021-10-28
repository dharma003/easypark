package com.org.easypark.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.easypark.model.SmsOtp;
import com.org.easypark.model.User;
import com.org.easypark.service.SMSServiceImpl;
import com.org.easypark.service.UserRegistrationServiceImpl;
import com.org.easypark.vo.Otp;
import com.org.easypark.vo.ParkInfo;
import com.org.easypark.vo.ReqParam;

@RestController
@RequestMapping("/user")
public class UserRegistrationController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserRegistrationController.class);
	
	@Autowired
	UserRegistrationServiceImpl userRegistrationServiceImpl;
	
	@Autowired
	SMSServiceImpl sMSServiceImpl;
	
	@PostMapping("/register")
	public String userRegistration(@Valid @RequestBody User user) {
		
		String register = userRegistrationServiceImpl.saveUserRegistration(user);
		
		LOG.info("SUCCESSFULLY REGISTERED");
		
		if(register!=null) {
		return register;	
		}
		
		return "Successfully registered";
		
	}
	
	@PostMapping("/login")
	public String userlogin(@Valid @RequestBody ReqParam reqParam) {

		String login = userRegistrationServiceImpl.login(reqParam);

		if (login != null) {

			LOG.info("LOGIN SUCCESSFULLY");
			return login;
		}

		return "Successfully login";

	}
	
	
	@PostMapping("/search")
	public List<ParkInfo> searchParkingArea(@Valid @RequestBody ReqParam reqParam) {
		
		List<ParkInfo> parkInfoList = userRegistrationServiceImpl.searchArea(reqParam);
		
		LOG.info("SEARCH SUCCESSFULLY");
		return parkInfoList;
		
	}
	
	
	@PostMapping("/sendOtp")
	public String sendSms(@Valid @RequestBody SmsOtp smsOtp) {
		
		String sendSms = sMSServiceImpl.sendSms(smsOtp);
		
		LOG.info("SEND OTP SUCCESSFULLY");
		return sendSms;
		
	}
	
	
	@PostMapping("/verifyOtp")
	public String verifyMobileNo(@Valid @RequestBody Otp otp) {
		
		String validate = sMSServiceImpl.validateOtp(otp.getMobileNo(),otp.getOtp());
		
		LOG.info("SEARCH SUCCESSFULLY");
		return validate;
		
	}

}
