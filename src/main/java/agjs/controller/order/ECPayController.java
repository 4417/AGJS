package agjs.controller.order;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import agjs.bean.order.SalesOrderHeaderPo;
import agjs.service.order.OrderProcessService;

@Controller
@RequestMapping(path = { "/main/ecpayprocess" })
public class ECPayController {

	@Autowired
	private OrderProcessService orderProcessService;

	@PostMapping(path = "/pay")
	public String ecpaySubmit(@RequestBody SalesOrderHeaderPo salesOrderHeaderPo) throws IOException {

		System.out.println("綠介 流程");
		String takeOrder = orderProcessService.callAllInOneService(salesOrderHeaderPo);
		System.out.println(takeOrder);
		return takeOrder;
	}

}
