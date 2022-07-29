package agjs.service;

import agjs.bean.user.UserPo;

public interface UserService {
	
	UserPo login(UserPo user);
	
	UserPo register(UserPo user);
}
