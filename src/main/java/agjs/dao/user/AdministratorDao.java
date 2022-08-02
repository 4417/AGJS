package agjs.dao.user;

import agjs.bean.user.AdministratorPo;

public interface AdministratorDao {

	AdministratorPo selectLogin(AdministratorPo administrator);

}