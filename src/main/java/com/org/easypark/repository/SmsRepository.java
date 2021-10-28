package com.org.easypark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.org.easypark.model.SmsOtp;




@Repository
public interface SmsRepository extends JpaRepository<SmsOtp, Long> {
	

	List<SmsOtp>  findByMobileNo(String mobileNo);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE SmsOtp s SET s.status = :status WHERE s.mobileNo = :mobileNo")
    int updateStatus(@Param("mobileNo") String mobileNo, @Param("status") String status);
	
	
	SmsOtp findByMobileNoAndStatus(String mobileNo,String status);
	 
}
