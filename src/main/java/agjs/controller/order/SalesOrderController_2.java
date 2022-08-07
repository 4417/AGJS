package agjs.controller.order;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.order.SalesOrderItemVo_2;
import agjs.bean.user.UserPo;
import agjs.dao.order.SalesOrderHeaderDao;
import agjs.service.order.SalesOrderHeaderService_2;

@RestController
@RequestMapping(path = {"/main/order"})
public class SalesOrderController_2 {
	@Autowired
	private SalesOrderHeaderService_2 service;
	
	//查詢使用者的訂單
	@PostMapping("/search/byUser")
	public List<SalesOrderItemVo_2> selectByUserId(@RequestBody SalesOrderItemVo_2 vo,HttpSession session){
		UserPo user= (UserPo) session.getAttribute("login");
		System.out.println("SalesOrderController ID："+user.getUserId());
		return service.selectByUserId(user.getUserId());
	}
	
	//查詢使用者的訂單明細：日期
	@PostMapping("/search/itemDate")
	public SalesOrderItemVo_2 selectForDate(@RequestBody SalesOrderItemVo_2 vo,HttpSession session){
		UserPo user= (UserPo) session.getAttribute("login");
		System.out.println(vo.getSalesOrderHeaderId());
		return service.selectForOrderDateItem(user.getUserId(),vo.getSalesOrderHeaderId());
	}
	
	//查詢使用者的訂單明細：房間明細
	@PostMapping("/search/roomItem")
	public List<SalesOrderItemVo_2> selectForRoom(@RequestBody SalesOrderItemVo_2 vo,HttpSession session){
		UserPo user= (UserPo) session.getAttribute("login");
		System.out.println(vo.getSalesOrderHeaderId());
		return service.selectForRoom(user.getUserId(),vo.getSalesOrderHeaderId());
	}
	
	//查詢使用者的訂單明細：行程明細
	@PostMapping("/search/journeyItem")
	public List<SalesOrderItemVo_2> selectForJourney(@RequestBody SalesOrderItemVo_2 vo,HttpSession session){
		UserPo user= (UserPo) session.getAttribute("login");
		System.out.println(vo.getSalesOrderHeaderId());
		return service.selectForJourney(user.getUserId(),vo.getSalesOrderHeaderId());
	}
}
