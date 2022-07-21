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

		res.setContentType("application/json;charset=UTF-8");

		Gson gson =new Gson();

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
			 

		} catch (NamingException e) {
			
			e.printStackTrace();
			respObj.addProperty("errMsg", "系統錯誤");
		}
		res.getWriter().append(gson.toJson(respObj));
		
		
		
		
	}
	
	

}
