package agjs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.order.SalesOrderHeaderPo;
import agjs.service.SalesOrderHeaderService;
import agjs.service.UserService;

@RestController
@RequestMapping(path = {"/main/order", "/admin/order"})
public class SalesOrderController {
	
	@Autowired
	private SalesOrderHeaderService service;

	
	
	//接收資料 (驗證資料、創建訂單移至service)
//	public String handlerMethod(Model model, HttpSession session, String USER_NAME, String USER_IDENTITYNUMBER, String USER_PHONE) {
//		Map<String, String> errors = new HashMap<String, String>();
//		model.addAttribute("errors", errors);
//		
//		if(USER_NAME == null || USER_NAME.length() == 0) {
//			errors.put("USER_NAME", "請填寫姓名");
//		}
//		if(USER_IDENTITYNUMBER == null || USER_IDENTITYNUMBER.length() == 0) {
//			errors.put("USER_IDENTITYNUMBER", "請填寫身分證字號");
//		} //新增一個比對如果資料庫有這個身分證字號 else if(USER_IDENTITYNUMBER == ) {errors.put("USER_IDENTITYNUMBER", "這個帳號好像有人使用了，是否需要登入?");}
//		
//		if(USER_PHONE == null || USER_PHONE.length() ==0) {
//			errors.put("USER_PHONE", "請填寫手機");
//		}
//		// else if(USER_PHONE) {
//			//手機要為09開頭
//		//	errors.put("USER_PHONE", "手機格式錯誤");
//		//}
//		if(errors!= null && errors.isEmpty()) {
//			return "reservation_details_.html";
//		}
//		
//		//session.setAttribute("user", bean);
//		
//		return "redirect: xxxx.html"; //成功就跳轉頁面
//	}
	
	@GetMapping("/create")
	public SalesOrderHeaderPo create(HttpServletRequest request, HttpServletResponse response) {
		//UserPo customer = UserService.getusername();
		//if登入
		
		return null;
	}
	
	//查詢
	@GetMapping("/search")
	public List<SalesOrderHeaderPo> getAll(HttpServletRequest request, HttpServletResponse response, Model model) {
		return service.getAll();
	}
	
	//刪除
	@GetMapping("/delete")
	public SalesOrderHeaderPo delete(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
