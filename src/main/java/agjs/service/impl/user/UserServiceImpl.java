package agjs.service.impl.user;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.user.UserPo;
import agjs.dao.user.UserDao;
import agjs.service.user.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao dao;
	
	@Transactional
	public UserPo login(UserPo user) throws UnsupportedEncodingException {
		final String account = user.getUserAccount();
		if(account==null||Objects.equals(account, "")) {
			user.setErrorMsg("帳號必須輸入");
			return user;
		}
		final String password = user.getUserPassword();
		if(password==null||Objects.equals(password, "")) {
			user.setErrorMsg("密碼必須輸入");
			return user;
		}
		//Base6登入密碼加密比對編碼(正式整合再打開)
//		final String password_login=user.getUserPassword();
//		final Base64.Encoder encoder = Base64.getEncoder();
//		final byte[] passwordByte = password_login.getBytes("UTF-8");
//		final String passwordText = encoder.encodeToString(passwordByte);
//		System.out.println("登入密碼加密="+passwordText);
//		user.setUserPassword(passwordText);
		
		//Base6登入密碼解密(自己測試)
//		final Base64.Decoder decoder = Base64.getDecoder();
//		String rePassword=new String(decoder.decode(passwordText), "UTF-8");
//		System.out.println("登入密碼解密="+rePassword);
		final UserPo result = dao.selectLogin(user);
		if(result==null) {
			user.setErrorMsg("帳號或密碼錯誤");
			return user;
		}
		return result;
	}
	
	@Transactional
	public UserPo register(UserPo user) throws UnsupportedEncodingException {
		String reg="^[0-9a-zA-Z]{4,25}$";
		String pwd_reg = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{4,25}$";
		String idty_reg = "^[A-Z]\\d{9}$";
		String phone_reg = "^09[0-9]{8}$";
		String mail_reg =
	      "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		if(user.getUserName()==null||Objects.equals(user.getUserName(), "")) {
			user.setErrorMsg("請輸入姓名");
		}else if(user.getUserBirthday()==null||Objects.equals(user.getUserBirthday(), "")) {
			user.setErrorMsg("請選擇生日");
		}else if(user.getUserIdentityNumber()==null||Objects.equals(user.getUserIdentityNumber(), "")) {
			user.setErrorMsg("請輸入身分證字號");
		}else if(user.getUserIdentityNumber()!=null && user.getUserIdentityNumber().matches(idty_reg)==false) {
			user.setErrorMsg("請輸入正確格式的身分證字號");
		}else if(user.getUserAccount()==null||Objects.equals(user.getUserAccount(), "")){
			user.setErrorMsg("請輸入帳號");
		}else if(user.getUserAccount()!=null && user.getUserAccount().matches(reg)==false){
			user.setErrorMsg("帳號格式需填寫大小寫英文、數字，長度為4-25碼");
		}else if(user.getUserPassword()==null||Objects.equals(user.getUserPassword(), "")){
			user.setErrorMsg("請輸入密碼");
		}else if(user.getUserPassword()!=null && user.getUserPassword().matches(pwd_reg)==false){
			user.setErrorMsg("密碼格式需包含英文大小寫、數字各1個以上，長度為4-25碼");
		}else if(user.getUserPhone()==null||Objects.equals(user.getUserPhone(), "")){
			user.setErrorMsg("請輸入手機");
		}else if(user.getUserPhone()!=null&&user.getUserPhone().matches(phone_reg)==false){
			user.setErrorMsg("請輸入正確格式的手機");
		}else if(user.getUserEmail()==null||Objects.equals(user.getUserEmail(), "")){
			user.setErrorMsg("請輸入信箱");
		}else if(user.getUserEmail()!=null&&user.getUserEmail().matches(mail_reg)==false){
			user.setErrorMsg("請輸入正確的信箱");
		}else {
			//檢查帳號是否已有其他人使用
			final String account = user.getUserAccount();
			UserPo accountResult = dao.selectByAccount(account);
			if(accountResult!=null) {
				user.setErrorMsg("此帳號已存在，請更換為其他帳號");
			}else {
				//Base6密碼加密
				final String password=user.getUserPassword();
				final Base64.Encoder encoder = Base64.getEncoder();
				final byte[] passwordByte = password.getBytes("UTF-8");
				final String passwordText = encoder.encodeToString(passwordByte);
				System.out.println(passwordText);
				user.setUserPassword(passwordText);
				
				user=dao.insert(user);
			}
		}
		return user;
	}
	
	@Transactional
	public UserPo update(UserPo user) {
		UserPo pastUser =dao.selectByAccount(user.getUserAccount());
		//若使用者無變更信箱，則不更新信箱驗證狀態，只更新其他資訊
		if(user.getUserEmail().equals(pastUser.getUserEmail())==true) {
			pastUser.setUserEmail(user.getUserEmail());
			pastUser.setUserPhone(user.getUserPhone());
			user=dao.update(pastUser);
			return user;
		//若有變更信箱，但無進入驗證，則將驗證狀態改為false
		}else if (user.getUserEmail().equals(pastUser.getUserEmail())==false){
			pastUser.setUserEmail(user.getUserEmail());
			pastUser.setUserPhone(user.getUserPhone());
			pastUser.setEmailVerifyStatus(false);
			user=dao.update(pastUser);
			return user;
		}else {
			user.setErrorMsg("系統錯誤");
			return user;
		}
	}
	
	//有更新信箱，且有同時驗證，則將驗證狀態更新為True(前端AJAX設定)
	@Transactional
	public UserPo updateIncludeVerify(UserPo user) {
		UserPo pastUser =dao.selectByAccount(user.getUserAccount());
		if(user.getUserAccount()!=null) {
			pastUser.setEmailVerifyStatus(user.getEmailVerifyStatus());
			pastUser.setUserEmail(user.getUserEmail());
			pastUser.setUserPhone(user.getUserPhone());
			user=dao.update(pastUser);
			return user;
		}
		user.setErrorMsg("系統錯誤");
		return user;
		
	}
	
	@Transactional
	public UserPo updatePwd(UserPo user) throws UnsupportedEncodingException {
		UserPo pastUser =dao.selectByAccount(user.getUserAccount());
		
		//Base6密碼加密
		final Base64.Encoder encoder = Base64.getEncoder();
		final String password=user.getUserPassword();
		final byte[] passwordByte = password.getBytes("UTF-8");
		final String passwordText = encoder.encodeToString(passwordByte);
		
		final String newPassword=user.getNewUserPassword();
		final byte[] newPasswordByte = newPassword.getBytes("UTF-8");
		final String newPasswordText = encoder.encodeToString(newPasswordByte);
		
		
		System.out.println("舊密碼加密="+passwordText);
		System.out.println("新密碼加密="+newPasswordText);
		//確認使用者輸入的舊密碼是否符合
		if(passwordText!=null && newPasswordText!=null && passwordText.equals(pastUser.getUserPassword())) {
			//確認使用者的新密碼不與舊密碼相同
			if(passwordText.equals(newPasswordText)) {
				user.setErrorMsg("新密碼不得與舊密碼相同，請重新輸入");
				return user;
			}else{
				pastUser.setUserPassword(newPasswordText);
				user=dao.update(pastUser);
				return user;
			}
		}else {
			user.setErrorMsg("舊密碼不符，請重新輸入");
			return user;
		}
	}
	
	@Transactional
	public UserPo updatePwdByEmail(UserPo user) throws UnsupportedEncodingException {
		UserPo pastUser =dao.selectByMail(user.getUserEmail());
		
		//Base6密碼加密
		final Base64.Encoder encoder = Base64.getEncoder();
		final String newPassword=user.getNewUserPassword();
		final byte[] newPasswordByte = newPassword.getBytes("UTF-8");
		final String newPasswordText = encoder.encodeToString(newPasswordByte);
		System.out.println("新密碼加密="+newPasswordText);
		
		if(newPasswordText!=null && user.getUserName().equals(pastUser.getUserName())) {
			if(newPasswordText.equals(pastUser.getUserPassword())) {
				user.setErrorMsg("新密碼不得與舊密碼相同，請重新輸入");
				return user;
			}else {
				pastUser.setUserPassword(newPasswordText);
				user=dao.update(pastUser);
				return user;
			}
		}else {
			user.setErrorMsg("資訊不符，請重新輸入");
			return user;
		}
	}
	
	@Transactional
	public UserPo selectByEmail(UserPo user) {
		final String mail = user.getUserEmail();
		UserPo mailResult = dao.selectByMail(mail);
		//驗證此信箱未被他人使用，若信箱已存在在同一個會員資料裡的話則為例外
		if(mailResult!=null && mailResult.getUserAccount().equals(user.getUserAccount())==false) {
			user.setErrorMsg("此信箱已存在，請更換為其他信箱");
			return user;
		}else {
			return user;
		}
	}
	
	@Transactional
	public UserPo selectByEmailFindPwd(UserPo user) {
		final String mail = user.getUserEmail();
		UserPo mailResult = dao.selectByMail(mail);
		//確認輸入的信箱與姓名符合，才發送重設密碼的驗證信
		if(mailResult!=null) {
			if(user.getUserName().equals(mailResult.getUserName())) {
				return user;
			}else {
				user.setErrorMsg("姓名不符，請重新輸入");
				System.out.println("user.getUserName()="+user.getUserName());
				System.out.println("mailResult.getUserName()="+mailResult.getUserName());
				return user;
			}
		}else {
			user.setErrorMsg("此信箱不存在，請重新輸入");
			return user;
		}
	}

}
