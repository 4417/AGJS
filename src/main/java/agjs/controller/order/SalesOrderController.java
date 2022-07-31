package agjs.controller.order;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.journey.JourneyItemSelectVo;
import agjs.bean.order.SalesOrderHeaderPo;
import agjs.service.order.SalesOrderHeaderService;
import agjs.service.user.UserService;

@RestController
@RequestMapping(path = {"/main/order", "/admin/order"})
public class SalesOrderController {
	
	@Autowired
	private SalesOrderHeaderService service;
	@Autowired
	private MessageSource messageSource;
	
//	@RequestMapping(path = {"/main/reservation_details_.html"})
//	public String handlerMethod(Model model, String username, HttpSession session) {
//		
//		Map<String, String> errors = new HashMap<String, String>();
//		model.addAttribute("errors", errors);
//		
//		if(username == null || username.length() == 0) {
//			errors.put("username", "請填寫姓名(Controller)");
//		}
//		if(errors!= null && errors.isEmpty()) {
//			return "成功畫面";
//		}
//		return "reservation_details_.html";
//	}
	
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
	
	//新增訂單
	@GetMapping("/create")
	public SalesOrderHeaderPo create() {
		//UserPo customer = UserService.getusername();
		//if登入
		
		return null;
	}
	
	//查詢所有訂單
	@PostMapping("/search")
	public List<SalesOrderHeaderPo> getAll(Model model) {
		return service.getAll();
	}

//依訂單起始日查詢，待完成
	@PostMapping("/search.byStartdate")
	public List<SalesOrderHeaderPo> selecctByOrderStartDate(Date date) {
		System.out.println("select order by start date(Controller):");
		System.out.println(date);
		return service.selecctByOrderStartDate(date);
	}
	
//查詢單張訂單包含行程與房型，待完成
	@PostMapping("/search.byOrderId")
		public SalesOrderHeaderPo selectById(Integer id) {
			//return service.selectById(id);
		return null;
	}
	
//查詢使用者的訂單，待完成，改成post?
	@GetMapping("/search.byUser")
	public List<SalesOrderHeaderPo> selectByUserId(Integer userId){
		return service.selectByUserId(userId);
	}
	
//刪除，待完成
	@DeleteMapping("/delete")
	public boolean delete(@RequestBody Integer id) {
		return service.delete(id);
	}

	
}
