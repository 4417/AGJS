package agjs.service.impl;

import java.util.Objects;

import javax.naming.NamingException;

import agjs.bean.UserPo;
import agjs.model.UserDao;
import agjs.model.impl.UserDaoImpl;
import agjs.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao dao;
	
	public UserServiceImpl() throws NamingException {
		dao = new UserDaoImpl();
	}
	
	public String login(UserPo user) {
		final String account = user.getUserAccount();
		System.out.println("Service account："+account);
		if(account==null||Objects.equals(account, "")) {
			
			return "帳號必須輸入";
		}
		final String password = user.getUserPassword();
		if(password==null||Objects.equals(password, "")) {
			return "密碼必須輸入";
		}
		
		final UserPo result = dao.selectLogin(user);
		if(result==null) {
			return "系統錯誤，請聯絡管理員";
		}
		return null;
		
	}

}
