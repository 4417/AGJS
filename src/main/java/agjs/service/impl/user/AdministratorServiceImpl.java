package agjs.service.impl.user;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.user.AdministratorPo;
import agjs.dao.user.AdministratorDao;
import agjs.service.user.AdministratorService;



@Service
public class AdministratorServiceImpl implements AdministratorService {
	@Autowired
	private AdministratorDao dao;
	
	@Transactional
	@Override
	public AdministratorPo login(AdministratorPo administrator) {
		final String account = administrator.getAdministratorAccount();
		if(account==null||Objects.equals(account, "")) {
			administrator.setErrorMsg("帳號必須輸入");
			return administrator;
		}
		final String password = administrator.getAdministratorPassword();
		if(password==null||Objects.equals(password, "")) {
			administrator.setErrorMsg("密碼必須輸入");
			return administrator;
		}
		final AdministratorPo result = dao.selectLogin(administrator);
		if(result==null) {
			administrator.setErrorMsg("帳號或密碼錯誤");
			return administrator;
		}
		return result;
	}
	
	

}
