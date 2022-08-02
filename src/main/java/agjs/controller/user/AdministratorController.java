package agjs.controller.user;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.user.AdministratorPo;
import agjs.bean.user.UserPo;
import agjs.dao.user.AdministratorDao;
import agjs.service.user.AdministratorService;

@RestController
@RequestMapping("/admin")
public class AdministratorController {
	
	@Autowired
	private AdministratorService service;
	@Autowired
	private AdministratorDao dao;
	
	//管理員登入
	@PostMapping("/login")
	public AdministratorPo login(@RequestBody AdministratorPo administrator, Model model,HttpServletRequest req,HttpSession session) {
		//先回servive驗證，再設置session值
		administrator= service.login(administrator);
		session.setAttribute("administratorLogin", administrator);
		
		//從session判斷使用者是否登入過
		Object verifySession = session.getAttribute("administratorLogin");
		System.out.println("administrator："+administrator);
		System.out.println("verifySession："+verifySession);
		
		//若登入錯誤則會跑出錯誤訊息
		if (verifySession == null) {
			System.out.println("無此會員");			
			return administrator;
		}else {
			//若登入成功時就取得 HttpSession，基於安全考量，在登入成功後改變 Session ID
			if(req.getSession(false)!=null) {
				req.changeSessionId();
				session.setAttribute("administratorLogin", administrator);
				System.out.println("登入後1："+session.getId());
			}else {
				session =req.getSession();
			}
		}
		return administrator;
	}
	
	@PostMapping("/logout")
	public void Logout(@RequestBody AdministratorPo administrator,HttpSession session) {

		session.removeAttribute("administratorLogin");
		System.out.println(session.getAttribute("administratorLogin"));
		
	}
	
	//忘記密碼的信箱驗證
	@PostMapping("/mail_verify_pwd")
	public String verifyPwd(@RequestBody AdministratorPo administrator) {
		AdministratorPo pastAdministrator =dao.selectByAccount(administrator);
		if(pastAdministrator!=null) {
			service.sendMail(administrator);
			return "請至信箱查看驗證碼";
		}
		return "帳號不符，請重新輸入";
	}
	
	//找回
	@PutMapping("/find_password")
	public AdministratorPo findPwd(@RequestBody AdministratorPo administrator) {
		System.out.println("Controller:"+administrator.getNewAdministratorPassword());
		//確認使用者是否有輸入驗證碼
		if(administrator.getVerifyMsg()==null||Objects.equals(administrator.getVerifyMsg(), "")) {
			administrator.setErrorMsg("請輸入驗證碼");
			return administrator;
		}else {
			service.verifyJedis(administrator);
			if(administrator.getVerifyMsg()!=null) {
				return administrator;
			}else {
				administrator=service.updatePwdByEmail(administrator);
				return administrator;
			}
		}
	}
	
}
