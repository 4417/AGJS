package agjs.service;

import agjs.bean.user.UserPo;

public interface UserService {
	//註冊可能會有失敗的原因，所以用String
	UserPo login(UserPo user);
}
