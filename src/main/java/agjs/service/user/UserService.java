package agjs.service.user;

import agjs.bean.user.UserPo;

public interface UserService {
	
	UserPo login(UserPo user);
	
	UserPo register(UserPo user);

	UserPo update(UserPo user);
	
	UserPo updateIncludeVerify(UserPo user);
	
	UserPo updatePwd(UserPo user);
	
	UserPo updatePwdByEmail(UserPo user);
	
	UserPo selectByEmail(UserPo user);
	
	UserPo selectByEmailFindPwd(UserPo user);
	
	
}
