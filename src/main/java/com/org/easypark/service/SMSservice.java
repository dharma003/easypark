package com.org.easypark.service;

import org.springframework.stereotype.Service;

import com.org.easypark.model.SmsOtp;

@Service
public interface SMSservice {
	
	public String sendSms(SmsOtp smsOtp);

}
