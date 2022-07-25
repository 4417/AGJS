package agjs.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agjs.service.CustomerServiceMailTableService;

@WebServlet("/admin/CustomerServiceMailTabledo")
public class CustomerServiceMailTableController extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		if ("delete".equals(action)) { // 來自mailListall.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer faqFormId = Integer.valueOf(req.getParameter("faqFormId"));
				
				/***************************2.開始刪除資料***************************************/
				CustomerServiceMailTableService customerServiceMailTableService = new CustomerServiceMailTableService();
				customerServiceMailTableService.deleteMail(faqFormId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = ("/admin/mailListall.jsp");
//				String url = ("http://localhost:8081/AGJS/admin/mailListall.jsp");
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}

	}
}
