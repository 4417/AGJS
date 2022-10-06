package agjs.ecpay.payment.integration.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ECPayController")
public class ECPayController extends HttpServlet {

	private static final long serialVersionUID = 1L;
//	private AllInOneService allInOneService;

	public ECPayController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 建立訂單
//		jsonElement = fromJson.get("ordersVO");
//		OrdersVO ordersVO = gson.fromJson(jsonElement, OrdersVO.class);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

//		allInOneService = new AllInOneServiceImpl();
//		String takeOrder = allInOneService.payment();

		// 清除session
		request.getSession().removeAttribute("checkout");
//		response.getWriter().append(takeOrder);

	}

}
