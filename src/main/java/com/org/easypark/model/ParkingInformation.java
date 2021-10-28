package com.org.easypark.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="park_info")
public class ParkingInformation {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "park_address")
	private String parkAddress;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "last_updated_by")
	private String lastUpdatedBy;
	
	@Column(name = "last_updated_date")
	private Date lastUpdatedDate;
	
	@Column(name = "park_type")
	private String parkType;  //M Y D
	
	@Column(name = "park_availability")
	private String parkAvailability; //Y N 
	
	@Column(name = "user_type")
	private String userType; //Requester Accepter 
	
	@Column(name = "parking_start_date")
	private Date parkingStartDate;
	
	@Column(name = "parking_end_date")
	private Date parkingEndDate;
	
	@Column(name = "status1")
	private String status1;   //Raise the request
	
	@Column(name = "status2")
	private String status2;   //Accepted or rejected
	
	@Column(name = "status3") // if accepted parking start
	private String status3;
	
	@Column(name = "status4") //else re request 
	private String status4;
	
	@Column(name = "status5")// parking completed
	private String status5;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getParkAddress() {
		return parkAddress;
	}

	public void setParkAddress(String parkAddress) {
		this.parkAddress = parkAddress;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getParkType() {
		return parkType;
	}

	public void setParkType(String parkType) {
		this.parkType = parkType;
	}

	public String getParkAvailability() {
		return parkAvailability;
	}

	public void setParkAvailability(String parkAvailability) {
		this.parkAvailability = parkAvailability;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getParkingStartDate() {
		return parkingStartDate;
	}

	public void setParkingStartDate(Date parkingStartDate) {
		this.parkingStartDate = parkingStartDate;
	}

	public Date getParkingEndDate() {
		return parkingEndDate;
	}

	public void setParkingEndDate(Date parkingEndDate) {
		this.parkingEndDate = parkingEndDate;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public String getStatus3() {
		return status3;
	}

	public void setStatus3(String status3) {
		this.status3 = status3;
	}

	public String getStatus4() {
		return status4;
	}

	public void setStatus4(String status4) {
		this.status4 = status4;
	}

	public String getStatus5() {
		return status5;
	}

	public void setStatus5(String status5) {
		this.status5 = status5;
	}
	
}