package agjs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.user.UserPo;

@RestController
@RequestMapping("/main/user")
public class LogoutController {
	
	@PostMapping("/logout")
	public void Logout(@RequestBody UserPo user,HttpSession session) {
		session.invalidate();
	}

}
