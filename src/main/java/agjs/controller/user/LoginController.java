package agjs.controller.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.user.UserPo;
import agjs.dao.user.UserDao;
import agjs.service.user.RegisterMailService;
import agjs.service.user.UserService;


@RestController
@RequestMapping("/main")
public class LoginController{
	@Autowired
	private UserService service;
	@Autowired
	private UserDao dao;
	@Autowired
	private RegisterMailService mailService;
	
	//會員登入
	@PostMapping("/login")
	public UserPo login(@RequestBody UserPo user, Model model,HttpServletRequest req,HttpSession session ) {
		//先回servive驗證，再設置session值
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
			//若登入成功時就取得 HttpSession，基於安全考量，在登入成功後改變 Session ID
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
	
	//會員註冊的信箱驗證
	@PostMapping("/mail_vertify")
	public String vertify(@RequestBody UserPo user) {
		mailService.sendMail(user);
		return "請至信箱查看驗證碼";
	}
	
	//會員註冊
	@PostMapping("/register")
	public UserPo register(@RequestBody UserPo user) {
		//驗證碼
		mailService.vertifyJedis(user);
		if(user.getVertifyMsg()!=null) {
			return user;
		}else {
			user = service.register(user);
			return user;
		}
		
	}
	

}
