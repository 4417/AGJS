package agjs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.order.SalesOrderStatusPo;
import agjs.service.SalesOrderStatusService;

@RestController
@RequestMapping(path = {"/main/order", "/admin/order"})
public class SalesOrderStatusController {
	
	@Autowired
	private SalesOrderStatusService service;
	
	@GetMapping("/status")
	public List<SalesOrderStatusPo> getallStatus() {
		return service.getAllStatus();
	}

}

