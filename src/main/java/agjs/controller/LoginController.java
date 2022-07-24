package agjs.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.UserPo;
import agjs.service.UserService;


@RestController
@RequestMapping("/main/user")
public class LoginController{
	@Autowired
	private UserService service;
	
	@PostMapping("/login")
	public UserPo login(@RequestBody UserPo user, Model model,HttpServletRequest req,HttpSession session ) {
		
		
		user= service.login(user);
		session.setAttribute("login", user.getUserId());
		//從session判斷使用者是否登入過
		Object verifySession = session.getAttribute("login");
		
		System.out.println("user："+user.getUserId());
		System.out.println("verifySession："+verifySession);
		//若登入錯誤則會跑出錯誤訊息
		if (verifySession == null) {
			System.out.println("無此會員");			
			return user;
		}else {
			//若登入成功時就取得 HttpSession
			//基於 Web 安全考量，建議在登入成功後改變 Session ID
			if(req.getSession(false)!=null) {
				req.changeSessionId();
				session.setAttribute("login", user);
				System.out.println("登入後1："+session.getId());
			}else {
				session =req.getSession();
			}
		}
		return user;
	}
}
