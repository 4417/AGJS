package agjs.controller.order;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

//	@PostMapping(path = "/pay")
//	public String ecpaySubmit(@RequestBody SalesOrderHeaderPo salesOrderHeaderPo) throws IOException {
//
//		System.out.println("綠介 流程");
//		String takeOrder = orderProcessService.callAllInOneService(salesOrderHeaderPo);
//		System.out.println(takeOrder);
//		return takeOrder;
//	}

	@PostMapping(path = "/dopay")
	public void doPost(@RequestBody SalesOrderHeaderPo salesOrderHeaderPo, HttpSession session,
			HttpServletResponse response) throws IOException {

		System.out.println("綠介 流程");
		String takeOrder = orderProcessService.callAllInOneService(salesOrderHeaderPo);
		System.out.println(takeOrder);
		if (takeOrder != null) {
			session.setAttribute("sohid", salesOrderHeaderPo.getSalesOrderHeaderId());
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().append(takeOrder);
		}

	}

	@PostMapping(path = "/paydone")
	public void ecpayFeedBack(@RequestBody String returnString, HttpServletResponse response, HttpSession session)
			throws IOException {

		System.out.println("綠介 支付成功 : " + returnString);
		Integer sohId = (Integer) session.getAttribute("sohid");
		System.out.println(sohId + "complete");
		try {
			if (sohId != null) {
				Integer status = orderProcessService.ecpayComplete(sohId);
				System.out.println("status:" + status);
			} else {
				System.out.println("取得id失敗");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(sohId + "更新失敗");
		} finally {
			session.removeAttribute("sohid");
		}

	}

}
