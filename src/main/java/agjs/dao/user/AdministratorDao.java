package agjs.dao.user;

import agjs.bean.user.AdministratorPo;
import agjs.bean.user.UserPo;

public interface AdministratorDao {

	AdministratorPo selectLogin(AdministratorPo administrator);

	AdministratorPo selectByAccount(AdministratorPo administrator);
	
	AdministratorPo updatePwd(AdministratorPo administrator);
}