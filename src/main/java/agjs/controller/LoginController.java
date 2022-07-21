package agjs.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public UserPo login(@RequestBody UserPo user) {
		return service.login(user);
	}
}
