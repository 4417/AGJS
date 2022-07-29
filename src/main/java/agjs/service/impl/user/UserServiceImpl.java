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
		
		
		final String account = user.getUserAccount();
		UserPo accountResult = dao.selectByAccount(account);
		if(accountResult==null) {
			user=dao.insert(user);
			return user;
		}else {
			user.setErrorMsg("此帳號已存在，請更換為其他帳號");
			return user;
		}
		
	}

}
