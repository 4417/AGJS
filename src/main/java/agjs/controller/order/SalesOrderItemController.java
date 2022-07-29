package agjs.controller.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import agjs.bean.order.SalesOrderItemPo;
import agjs.service.order.SalesOrderItemService;
public class SalesOrderItemController {

	@Autowired
	private SalesOrderItemService service;
	
	@PostMapping("/itemsearch")
	public List<SalesOrderItemPo> getOrderItemByHeaderId(HttpServletRequest req, HttpServletResponse res) {
		
		return service.getOrderItemByHeaderId(100);
	}
}
