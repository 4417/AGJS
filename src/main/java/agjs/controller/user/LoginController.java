package agjs.controller.user;


import java.io.UnsupportedEncodingException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public UserPo login(@RequestBody UserPo user, Model model,HttpServletRequest req,HttpSession session ) throws UnsupportedEncodingException {
		//先回servive驗證，再設置session值
		user= service.login(user);
		session.setAttribute("login", user);
		
		//從session判斷使用者是否登入過
		Object verifySession = session.getAttribute("login");
		System.out.println("user："+user);
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
	@PostMapping("/mail_verify")
	public String verify(@RequestBody UserPo user) {
		//先確認信箱有無其他人使用
		user=service.selectByEmail(user);
		if(user.getErrorMsg()!=null) {
			return user.getErrorMsg();
		}else {
			mailService.sendMail(user);
			return "請至信箱查看驗證碼";
		}
		
	}
	
	//忘記密碼的信箱驗證
	@PostMapping("/mail_verify_pwd")
	public String verifyPwd(@RequestBody UserPo user) {
		//先確認信箱有無其他人使用
		user=service.selectByEmailFindPwd(user);
		if(user.getErrorMsg()!=null) {
			return user.getErrorMsg();
		}else {
			mailService.sendMail(user);
			return "請至信箱查看驗證碼";
		}
		
	}
	
	//會員註冊
	@PostMapping("/register")
	public UserPo register(@RequestBody UserPo user) throws UnsupportedEncodingException {
		//確認是否有在時間限制內輸入驗證碼，有才註冊成功
		mailService.verifyJedis(user);
		if(user.getVerifyMsg()!=null) {
			return user;
		}else {
			user = service.register(user);
			return user;
		}
	}
	
	//會員資訊管理
	@PostMapping("/user/information")
	public UserPo getByAccount(@RequestBody UserPo user,HttpSession session) {
		//從session來找是哪位會員，並顯示此會員的資料
		user = (UserPo) session.getAttribute("login");
		System.out.println("account:"+user.getUserAccount());
		user=dao.selectByAccount(user.getUserAccount());
		return user;
	}
	
	//會員資訊修改
	@PutMapping("/user/information_update")
	public UserPo updateUser(@RequestBody UserPo user) {
		System.out.println("Controller:"+user.getVerifyMsg());
		//先確認信箱有無其他人使用
		user=service.selectByEmail(user);
		if(user.getErrorMsg()!=null) {
			return user;
		}else {
			//確認使用者是否有輸入驗證碼，若沒有則不更新驗證狀態，只更新其他資訊
			if(user.getVerifyMsg()==null||Objects.equals(user.getVerifyMsg(), "")) {
				user=service.update(user);
				return user;
			}else {
				//若有輸入驗證碼，則確認是否是在時間內輸入
				mailService.verifyJedis(user);
				if(user.getVerifyMsg()!=null) {
					return user;
				}else {
					user=service.updateIncludeVerify(user);
					return user;
				}
			}
		}
	}
	
	//密碼修改
	@PutMapping("/user/password_update")
	public UserPo updatePwd(@RequestBody UserPo user) throws UnsupportedEncodingException {
		System.out.println("Controller:"+user.getNewUserPassword());
		user=service.updatePwd(user);
		
		return user;
	}
	
	//找回
	@PutMapping("/user/find_password")
	public UserPo findPwd(@RequestBody UserPo user) throws UnsupportedEncodingException {
		System.out.println("Controller:"+user.getNewUserPassword());
		//確認使用者是否有輸入驗證碼
		if(user.getVerifyMsg()==null||Objects.equals(user.getVerifyMsg(), "")) {
			user.setErrorMsg("請輸入驗證碼");
			return user;
		}else {
			mailService.verifyJedis(user);
			if(user.getVerifyMsg()!=null) {
				return user;
			}else {
				user=service.updatePwdByEmail(user);
				return user;
			}
		}
	}
	

}
