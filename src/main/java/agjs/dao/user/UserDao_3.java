package agjs.dao.user;

import agjs.bean.user.UserPo;
import agjs.dao.CoreDao;

public interface UserDao_3 extends CoreDao<UserPo, Integer> {

	UserPo selectOrderUser(UserPo user);
}
