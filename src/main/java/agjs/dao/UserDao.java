package agjs.dao;

import agjs.bean.UserPo;

public interface UserDao {

	//登入輸帳密抓出符合的會員資料
	//select * from USER where USER_ACCOUNT = ? and USER_PASSWORD = ?
	UserPo selectLogin(UserPo user);

}