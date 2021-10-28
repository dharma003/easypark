package com.org.easypark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.org.easypark.model.User;




@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserName(String userName);

	@Query("SELECT u from User u where u.address1 LIKE CONCAT('%',:address1,'%') or u.address2 LIKE CONCAT('%',:address2,'%')")
	List<User> findByAddress1AndAddress2(@Param("address1") String address1,@Param("address2") String address2);
	
}
