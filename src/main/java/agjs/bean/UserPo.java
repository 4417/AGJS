package agjs.bean;

import java.sql.Date;

public class UserPo {
	
	private Integer userId;
	private String userAccount;
	private String userPassword;
	private String userName;
	private java.sql.Date userBirthday;
	private String userEmail;
	private String userPhone;
	private Boolean emailVerifyStatus;
	private String userIdentityNumber;
	private java.sql.Date userRegistrationDate;
	public UserPo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserPo(Integer userId, String userAccount, String userPassword, String userName, Date userBirthday,
			String userEmail, String userPhone, Boolean emailVerifyStatus, String userIdentityNumber,
			Date userRegistrationDate) {
		super();
		this.userId = userId;
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userBirthday = userBirthday;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.emailVerifyStatus = emailVerifyStatus;
		this.userIdentityNumber = userIdentityNumber;
		this.userRegistrationDate = userRegistrationDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public java.sql.Date getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(java.sql.Date userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Boolean getEmailVerifyStatus() {
		return emailVerifyStatus;
	}
	public void setEmailVerifyStatus(Boolean emailVerifyStatus) {
		this.emailVerifyStatus = emailVerifyStatus;
	}
	public String getUserIdentityNumber() {
		return userIdentityNumber;
	}
	public void setUserIdentityNumber(String userIdentityNumber) {
		this.userIdentityNumber = userIdentityNumber;
	}
	public java.sql.Date getUserRegistrationDate() {
		return userRegistrationDate;
	}
	public void setUserRegistrationDate(java.sql.Date userRegistrationDate) {
		this.userRegistrationDate = userRegistrationDate;
	}
	
	

}
