package agjs.service.user;

import agjs.bean.user.AdministratorPo;

public interface AdministratorService {

	AdministratorPo login(AdministratorPo administrator);
	
	void sendMail(AdministratorPo administrator);
	
	AdministratorPo verifyJedis(AdministratorPo administrator);

	AdministratorPo updatePwdByEmail(AdministratorPo administrator);
}