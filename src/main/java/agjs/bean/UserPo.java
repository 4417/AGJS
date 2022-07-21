package agjs.bean;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "USER")
public class UserPo {
	
//	USER_ID, USER_ACCOUNT, USER_PASSWORD, USER_NAME, USER_BIRTHDAY, USER_EMAIL, USER_PHONE, EMAIL_VERIFY_STATUS, USER_IDENTITYNUMBER, USER_RIGISTRATION_DATE
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;
	@Column(name = "USER_ACCOUNT")
	private String userAccount;
	@Column(name = "USER_PASSWORD")
	private String userPassword;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "USER_BIRTHDAY")
	private java.sql.Date userBirthday;
	@Column(name = "USER_EMAIL")
	private String userEmail;
	@Column(name = "USER_PHONE")
	private String userPhone;
	@Column(name = "EMAIL_VERIFY_STATUS")
	private Boolean emailVerifyStatus;
	@Column(name = "USER_IDENTITYNUMBER")
	private String userIdentityNumber;
	@Column(name = "USER_RIGISTRATION_DATE")
	private java.sql.Date userRegistrationDate;
	@Transient
	private String errorMsg;
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
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
