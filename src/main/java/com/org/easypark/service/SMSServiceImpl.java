package com.org.easypark.service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.easypark.model.SmsOtp;
import com.org.easypark.repository.SmsRepository;
import com.org.easypark.repository.UserRepository;
@Service
public class SMSServiceImpl implements SMSservice{

	private static final String OTPMESSAGE="Dear Customer , Kindly provide this OTP for your mobile number verifycation to the EASYPARK. The OTP is ";
	private static final Random generator = new Random();
	private static final String APIKEY="OsIFbfr1RL0-8A1CWeupApB0sQ7cK9kSHsKZFDD951";
	private static final String SMSSTATUSINACTIVE="InActive";
	private static final String SMSSTATUSACTIVE="Active";
	
	@Autowired
	SmsRepository smsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public static void main(String ...s) {
		
		//SMSServiceImpl sMSServiceImpl  = new SMSServiceImpl();
		//System.out.println(sMSServiceImpl.sendSms("919488545767",""));
		
		System.out.println(generatePin());
	}
	
	@Override
	@Transactional
	public String sendSms(SmsOtp smsOtp) {
		final StringBuffer stringBuffer = new StringBuffer();
		
		List<SmsOtp> mobileList = smsRepository.findByMobileNo(smsOtp.getMobileNo());
		
		try {

			if(mobileList.size()>0) {
				
				//update Inactive if there in old sms our database.
				
				smsRepository.updateStatus(smsOtp.getMobileNo(), SMSSTATUSINACTIVE);
			}
			
			
				int generatePin = generatePin();

				
				// Construct data
				String apiKey = "apikey=" + APIKEY;
				String message = "&message=" + OTPMESSAGE + generatePin;
				String sender = "&sender=" + "TXTLCL"; // esyprk
				String numbers = "&numbers=" + smsOtp.getMobileNo();

				// Send data
				HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?")
						.openConnection();
				String data = apiKey + numbers + message + sender;
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
				conn.getOutputStream().write(data.getBytes("UTF-8"));
				final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String line;
				while ((line = rd.readLine()) != null) {
					stringBuffer.append(line);
				}
				rd.close();
				
				String receivedMsg = stringBuffer.toString();
				
				// save the OTP information
				
				smsOtp.setStatus("Active");
				smsOtp.setOtp(generatePin);
				
				smsRepository.save(smsOtp);
				
			//}
			return receivedMsg;

		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			return "Error " + e;
		}
	}
	
	public static int generatePin() {
	    return 100000 + generator.nextInt(900000);
	}
	
	
	
	public String validateOtp(String mobileNo,int Otp) {
		
		
		SmsOtp smsOtp = smsRepository.findByMobileNoAndStatus(mobileNo, SMSSTATUSACTIVE);
		
		if(smsOtp!=null) {
			
			if(smsOtp.getOtp()==Otp) {
				
				return "success";
			}
		}
		
		return "failure";
	}

}
