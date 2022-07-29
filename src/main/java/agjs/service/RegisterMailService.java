package agjs.service;

import agjs.bean.user.UserPo;

public interface RegisterMailService {

	void sendMail(UserPo user);
	
	UserPo vertifyJedis(UserPo user);

}