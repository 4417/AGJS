package agjs.dao.user;

import agjs.bean.user.UserPo;

public interface UserDao {

	//帳密查詢並登入
	UserPo selectLogin(UserPo user);
	
	//帳號查詢
	UserPo selectByAccount(String account);
	
	//註冊新增
	UserPo insert(UserPo user);
	
	//會員資料更新
	UserPo update(UserPo user);

}