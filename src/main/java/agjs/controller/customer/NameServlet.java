package agjs.controller.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main/namecheck")
public class NameServlet extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String userName = req.getParameter("userName");
		
		System.out.println("確認使用者傳入名稱="+ userName);
		if (userName.trim().isEmpty()){
			return;
		}else {
			req.setAttribute("userName", userName);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/main/chat.jsp");
			dispatcher.forward(req, res);
		}
	}
}
