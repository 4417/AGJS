package agjs.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.user.AdministratorPo;
import agjs.service.user.AdministratorService;

@RestController
@RequestMapping("/admin")
public class AdministratorController {
	
	@Autowired
	private AdministratorService service;
	
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
	
}
