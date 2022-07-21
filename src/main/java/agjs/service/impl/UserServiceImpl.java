package agjs.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.UserPo;
import agjs.model.UserDao;
import agjs.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao dao;
	
	@Transactional
	public UserPo login(UserPo user) {
		final String account = user.getUserAccount();
		System.out.println("Service account："+account);
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

}
