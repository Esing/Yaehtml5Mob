package com.yaeyi.mobile.service.shiro;

import java.io.Serializable;

public class UserManage   implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer userID;
	private Integer patientID;
	private String userAccount;
	private String userPassword;
	private String userName;
	private String userGender;
	private String userPhone;
	private String userICON;
	private String userAddress;
	private Integer activeOrder;
	private Integer paySecurity;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getPatientID() {
		return patientID;
	}

	public void setPatientID(Integer patientID) {
		this.patientID = patientID;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserICON() {
		return userICON;
	}

	public void setUserICON(String userICON) {
		this.userICON = userICON;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Integer getActiveOrder() {
		return activeOrder;
	}

	public void setActiveOrder(Integer activeOrder) {
		this.activeOrder = activeOrder;
	}

	public Integer getPaySecurity() {
		return paySecurity;
	}

	public void setPaySecurity(Integer paySecurity) {
		this.paySecurity = paySecurity;
	}
}