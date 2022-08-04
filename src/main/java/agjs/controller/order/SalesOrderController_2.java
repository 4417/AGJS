package agjs.controller.order;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.user.UserPo;
import agjs.dao.order.SalesOrderHeaderDao;
import agjs.service.order.SalesOrderHeaderService_2;

@RestController
@RequestMapping(path = {"/main/order"})
public class SalesOrderController_2 {
	@Autowired
	private SalesOrderHeaderDao dao;
	@Autowired
	private SalesOrderHeaderService_2 service;
	
	//查詢使用者的訂單，待完成
	@PostMapping("/search/byUser")
//	public List<SalesOrderHeaderPo> selectByUserId(@RequestBody SalesOrderHeaderPo header,HttpSession session){
	public List<SalesOrderHeaderPo> selectByUserId(){
//		UserPo user= (UserPo) session.getAttribute("login");
//		System.out.println("SalesOrderController ID："+user.getUserId());
//		header.setUserId(user.getUserId());
		return service.selectByUserId(1);
	}
}
