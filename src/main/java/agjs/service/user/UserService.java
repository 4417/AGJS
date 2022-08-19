package agjs.service.user;

import java.io.UnsupportedEncodingException;

import agjs.bean.user.UserPo;

public interface UserService {
	
	UserPo login(UserPo user) throws UnsupportedEncodingException;
	
	UserPo register(UserPo user) throws UnsupportedEncodingException;

	UserPo update(UserPo user);
	
	UserPo updateIncludeVerify(UserPo user);
	
	UserPo updatePwd(UserPo user) throws UnsupportedEncodingException;
	
	UserPo updatePwdByEmail(UserPo user) throws UnsupportedEncodingException;
	
	UserPo selectByEmail(UserPo user);
	
	UserPo selectByEmailFindPwd(UserPo user);
	
	
}
