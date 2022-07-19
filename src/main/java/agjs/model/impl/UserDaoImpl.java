package agjs.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import agjs.bean.UserPo;
import agjs.model.UserDao;

public class UserDaoImpl implements UserDao {
	
	private DataSource dataSource;
	
	//登入
	public UserDaoImpl() throws NamingException {
		dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/AGJS");
		
	}
	
	//登入輸帳密抓出符合的會員資料
	//select * from USER where USER_ACCOUNT = ? and USER_PASSWORD = ?
	@Override
	public UserPo selectLogin(UserPo user) {
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pst = conn.prepareStatement("select * from USER where USER_ACCOUNT = ? and USER_PASSWORD = ?");
			pst.setString(1, user.getUserAccount());
			pst.setString(2, user.getUserPassword());
			//從資料庫回傳資料
			try(ResultSet rs =pst.executeQuery()){
				if(rs.next()) {
					UserPo resultUser = new UserPo();
					resultUser.setUserAccount(rs.getString("USER_ACCOUNT"));
					resultUser.setUserPassword(rs.getString("USER_PASSWORD"));
					resultUser.setUserBirthday(rs.getDate("USER_BIRTHDAY"));
					resultUser.setEmailVerifyStatus(rs.getBoolean("EMAIL_VERIFY_STATUS"));
					return resultUser;
				}
				
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
		
	}

}
