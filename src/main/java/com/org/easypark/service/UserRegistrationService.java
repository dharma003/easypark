package com.org.easypark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.org.easypark.model.User;
import com.org.easypark.vo.ParkInfo;
import com.org.easypark.vo.ReqParam;

@Service
public interface UserRegistrationService {
	
	
	public String saveUserRegistration(User user);
	
	public String login(ReqParam reqParam);
	
	public List<ParkInfo> searchArea(ReqParam reqParam);

}
