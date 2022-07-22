package agjs.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agjs.bean.CustomerServiceMailVO;
import agjs.service.CustomerServiceMailService;
import agjs.service.CustomerServiceMailTableService;


@WebServlet("/main/CustomerServiceMail")
public class CustomerServiceMailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//確保編碼
	req.setCharacterEncoding("UTF-8");
	
	//取出表單元素
	String select = req.getParameter("data_category_title");
	String username = req.getParameter("data_name");
	String userphone = req.getParameter("data_telephone");
	String usermail = req.getParameter("data_email");
	String userexp = req.getParameter("data_exp");
	
	//驗證取得的元素
	System.out.println("======= 本次取得元素 ======");
	System.out.println("問題種類:" + select);
	System.out.println("客戶姓名:" + username);
	System.out.println("客戶手機:" + userphone);
	System.out.println("客戶郵箱:" + usermail);
	System.out.println("客戶說明內容:" + userexp);
	
	//設定接收者
	String to = "dennis905078@gmail.com";
	
	//設定送標題與內容格式
	String subject = select;
	String messageText = "客戶姓名:"+ username +"<br>"+"客戶手機:"+ userphone +"<br>"+"客戶郵箱:"+ usermail +"<br><br>"+"客戶說明內容:"+"<br>"+ userexp ;
	
	//確認發送的內容
//	System.out.println("確認發送的內容");
//	System.out.println(messageText);
	
	//set新增元素(未完工)
//	CustomerServiceMailVO customerServiceMailVO = new CustomerServiceMailVO();
//	customerServiceMailVO.setFaqFormId(null);
//	customerServiceMailVO.setFaqTypeName(select);
//	customerServiceMailVO.setUserName(username);
//	customerServiceMailVO.setUserPhone(userphone);
//	customerServiceMailVO.setUserEmail(usermail);
//	customerServiceMailVO.setContentText(userexp);
//	customerServiceMailVO.setCreateDate(null);
	
	//設定過濾用條件
	String vphone = "^(09)[0-9]{8}$";
	String vmail = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
	
	if(select.trim().isEmpty() || username.trim().isEmpty() || userphone.trim().isEmpty() || usermail.trim().isEmpty() || userexp.trim().isEmpty()) {
		System.out.println("欄位不得為空");
		return;
	} else if(!userphone.trim().matches(vphone)){
		System.out.println("手機格式錯誤");
		return;
	} else if(!usermail.trim().matches(vmail)){
		System.out.println("信件格式錯誤");
		return;
	} else {
		//執行寄信方法
		try {
			System.out.println("格式確認成功,開始呼叫寄信");
			CustomerServiceMailService mailService = new CustomerServiceMailService();
			mailService.sendMail(to, subject, messageText);
			System.out.println("寄信完成");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("寄信失敗");
		}
		//執行新增方法(未完工)
//		try {
//			System.out.println("格式確認成功,開始呼叫新增");
//			CustomerServiceMailTableService customerServiceMailTableService = new CustomerServiceMailTableService();
//			customerServiceMailVO = customerServiceMailTableService.addMail(null, vphone, username, userphone, userexp, vmail, null);
//			System.out.println("新增完成");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("新增失敗");
//		}
	}
	
	}

}
