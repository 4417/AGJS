package agjs.service;

import agjs.bean.UserPo;

public interface UserService {
	//註冊可能會有失敗的原因，所以用String
	String login(UserPo user);
}
