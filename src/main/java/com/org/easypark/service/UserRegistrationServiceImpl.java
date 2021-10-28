package com.org.easypark.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.easypark.model.User;
import com.org.easypark.repository.UserRepository;
import com.org.easypark.util.PasswordUtil;
import com.org.easypark.vo.ParkInfo;
import com.org.easypark.vo.ReqParam;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{

	private static final int SALT_LENGTH=16;
	private static final Logger LOG = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;
	
	public String saveUserRegistration(User user) {

		User user1 = userRepository.findByUserName(user.getUserName());

		if (user1==null) {

			// Calling the util method for generate the salt and hash for password:

			String salt = PasswordUtil.generateSalt(SALT_LENGTH);

			user.setPasswordSalt(salt);
			user.setPasswordHash(PasswordUtil.hashPassword(user.getPassword(), salt));
			user.setPassword(null);
			userRepository.save(user);

			LOG.info("SUCCESSFULLY SAVED IN DATABASE:");

		}else {
			
			return "Already registered in this user name";
		}

		// userRepository.save(user);

		return "Successfully Registered";
	}

	@Override
	public String login(ReqParam reqParam) {
		
		User user = userRepository.findByUserName(reqParam.getUserName());
		
		if(user!=null && user.getUserName()!=null) {
			
			boolean verifyPassword = PasswordUtil.verifyPassword(reqParam.getPassword(),user.getPasswordHash(),user.getPasswordSalt());
		
			if(verifyPassword) {
				
				return "successfully login";
			}else {
				
				return "User name or password in correct.";
			}
		}
		
		return null;
	}

	@Override
	public List<ParkInfo> searchArea(ReqParam reqParam) {

		List<User> userList = userRepository.findByAddress1AndAddress2(reqParam.getKeyword(), reqParam.getKeyword());

		List<ParkInfo> parkList = new ArrayList<ParkInfo>();

		if (userList.size() > 0) {

			userList.forEach((User user) -> {

				ParkInfo parkInfo = new ParkInfo();

				parkInfo.setAddress1(user.getAddress1());
				parkInfo.setAddress2(user.getAddress2());
				parkInfo.setParkAvailability(user.getParkAvailability());
				parkInfo.setParkOwnerMobileNo(user.getMobileNo());
				parkInfo.setCity(user.getCity());
				parkInfo.setCountry(user.getCountry());
				parkList.add(parkInfo);
			});

		}

		return parkList;
	}

}
