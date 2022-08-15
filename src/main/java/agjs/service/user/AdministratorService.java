package agjs.service.user;

import java.io.UnsupportedEncodingException;

import agjs.bean.user.AdministratorPo;

public interface AdministratorService {

	AdministratorPo login(AdministratorPo administrator) throws UnsupportedEncodingException;
	
	void sendMail(AdministratorPo administrator);
	
	AdministratorPo verifyJedis(AdministratorPo administrator);

	AdministratorPo updatePwdByEmail(AdministratorPo administrator) throws UnsupportedEncodingException;
}