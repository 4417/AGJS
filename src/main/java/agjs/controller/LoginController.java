package agjs.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import agjs.bean.UserPo;
import agjs.model.UserDao;
import agjs.model.impl.UserDaoImpl;
import agjs.service.UserService;
import agjs.service.impl.UserServiceImpl;


@WebServlet("/main/user/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		//要將Service傳回來的錯誤訊息改成編碼UTF-8
		res.setContentType("application/json;charset=UTF-8");
		//使用Gson將Json格式字串資料轉為Java物件
		Gson gson =new Gson();
		//錯誤訊息要改成{"errMsg":"帳號必須輸入"}的格式
		//JsonObject就像黏土，想捏成什麼形狀都可以，也不用再建一個VO
		JsonObject respObj = new JsonObject();

		try {
			UserPo user = new UserPo();
			user.setUserAccount(req.getParameter("account"));
			user.setUserPassword(req.getParameter("password"));
			
			//將Service的資料傳回來
			//servlet裡面不是出現DAO，而是會出現Service
			UserService service = new UserServiceImpl();
			//若註冊有錯誤，則回傳錯誤訊息至前端
//			UserDao dao = new UserDaoImpl();
			
			final String errMsg = service.login(user);
			respObj.addProperty("errMsg", errMsg);
			 
//			user.setUserAccount(account);
//			user.setUserPassword(password);
//			user=dao.selectLogin(user);
//			System.out.println(user.getUserAccount());
//			System.out.println(user.getUserPassword());
//			System.out.println(user.getUserBirthday());
//			System.out.println(user.getEmailVerifyStatus());
			
			//使用Gson將DAO的資料轉成JSON格式字串
//			String jsonstr = gson.toJson(user);
//			res.getWriter().append(jsonstr);
		} catch (NamingException e) {
			
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}
		res.getWriter().append(gson.toJson(respObj));
		
		
		
		
	}
	
	

}
