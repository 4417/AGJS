package agjs.service.impl.user;

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
	public UserPo login(UserPo user) {
		final String account = user.getUserAccount();
//		System.out.println("Service account："+account);
		if(account==null||Objects.equals(account, "")) {
			user.setErrorMsg("帳號必須輸入");
			return user;
		}
		final String password = user.getUserPassword();
		if(password==null||Objects.equals(password, "")) {
			user.setErrorMsg("密碼必須輸入");
			return user;
		}
		final UserPo result = dao.selectLogin(user);
		if(result==null) {
			user.setErrorMsg("帳號或密碼錯誤");
			return user;
		}
		return result;
	}
	
	@Transactional
	public UserPo register(UserPo user) {
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
			final String account = user.getUserAccount();
			UserPo accountResult = dao.selectByAccount(account);
			if(accountResult!=null) {
				user.setErrorMsg("此帳號已存在，請更換為其他帳號");
			}else {
				user=dao.insert(user);
			}
		}
		return user;
	}
	
	@Transactional
	public UserPo update(UserPo user) {
		System.out.println("Service帳號:"+user.getUserAccount());
		UserPo pastUser =dao.selectByAccount(user.getUserAccount());
		System.out.println("Service會員:"+user);
		if(user.getUserAccount()!=null) {
			pastUser.setUserEmail(user.getUserEmail());
			pastUser.setUserPhone(user.getUserPhone());
			user=dao.update(pastUser);
			return user;
		}
		user.setErrorMsg("系統錯誤");
		return user;
	}
	
	@Transactional
	public UserPo updateIncludeVerify(UserPo user) {
		System.out.println("IncludeVerify帳號:"+user.getUserAccount());
		UserPo pastUser =dao.selectByAccount(user.getUserAccount());
		System.out.println("IncludeVerify會員:"+user);
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
	public UserPo updatePwd(UserPo user) {
		System.out.println("Service帳號:"+user.getUserAccount());
		UserPo pastUser =dao.selectByAccount(user.getUserAccount());
		System.out.println("Service會員:"+user);
		if(user.getUserPassword()!=null && user.getNewUserPassword()!=null && user.getUserPassword().equals(pastUser.getUserPassword())) {
			pastUser.setUserPassword(user.getNewUserPassword());
			user=dao.update(pastUser);
			return user;
		}
		user.setErrorMsg("舊密碼不符，請重新輸入");
		return user;
	}
	
	@Transactional
	public UserPo updatePwdByEmail(UserPo user) {
		UserPo pastUser =dao.selectByMail(user.getUserEmail());
		System.out.println("Service會員:"+user);
		if(user.getNewUserPassword()!=null && user.getUserName().equals(pastUser.getUserName())) {
			pastUser.setUserPassword(user.getNewUserPassword());
			user=dao.update(pastUser);
			return user;
		}
		user.setErrorMsg("資訊不符，請重新輸入");
		return user;
	}
	
	@Transactional
	public UserPo selectByEmail(UserPo user) {
		final String mail = user.getUserEmail();
		UserPo mailResult = dao.selectByMail(mail);
		if(mailResult!=null) {
			user.setErrorMsg("此信箱已存在，請更換為其他信箱");
			return user;
		}else {
			return user;
		}
	}

}
