package agjs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.SalesOrderHeaderPo;
import agjs.service.SalesOrderHeaderService;

@RestController
@RequestMapping("/main/salesorder")
public class SalesOrderController {

	@Autowired
	private SalesOrderHeaderService service;

	@GetMapping("/header")
	public List<SalesOrderHeaderPo> getAll() {
		return service.getAll();
	}

}
