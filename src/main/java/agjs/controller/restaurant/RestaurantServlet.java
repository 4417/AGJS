package agjs.controller.restaurant;

import java.io.IOException;
import java.sql.Blob;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agjs.bean.restaurant.RestaurantVo;
import agjs.service.restaurant.RestaurantADService;
import agjs.service.restaurant.RestaurantService;

@WebServlet("/admin/restaurant")
public class RestaurantServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

//		String action = req.getParameter("action");
//		  if ("getImg".equals(action)) {
//			 RestaurantADDao dao = new RestaurantADDao();
//		   String adId = req.getParameter("adId");
//		   if (adId != null) {
//		    Integer i1 = Integer.parseInt(adId);
//		    RestaurantADVO vo = dao.findByPrimaryKey(i1);
//		    ServletOutputStream out = res.getOutputStream();
//		    out.write(vo.getAdPic());
//		    out.flush();
//		    out.close();
//		   }
//		  }

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("jsp in this doPost");
		
		/***************************************************************************************************************************************************/
// 		來自update.jsp的請求
		if ("update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer REST_ID = Integer.valueOf(req.getParameter("REST_ID").trim());
			String REST_NAME = req.getParameter("REST_NAME");
//			String REST_NAMEReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
//			if (REST_NAME == null || REST_NAME.trim().length() == 0) {
//				errorMsgs.add("餐廳名稱: 請勿空白");
//				System.out.println("餐廳名稱: 請勿空白");
//			} else if (!REST_NAME.trim().matches(REST_NAMEReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("餐廳名稱長度必需在2到100字之間");
//				System.out.println("餐廳名稱長度必需在2到100字之間");
//			}
			String REST_FLOOR = req.getParameter("REST_FLOOR").trim();
			if (REST_FLOOR == null || REST_FLOOR.trim().length() == 0) {
				errorMsgs.add("樓層請勿空白");
				System.out.println("樓層請勿空白");
			}

			String REST_TIME = req.getParameter("REST_TIME").trim();
			if (REST_TIME == null || REST_TIME.trim().length() == 0) {
				errorMsgs.add("日期內容請填入xx:xx-xx:xx");
				System.out.println("日期內容請填入xx:xx-xx:xx");
			}
			String REST_INTRO = req.getParameter("REST_INTRO").trim();

			java.sql.Date INTRO_TIME = java.sql.Date.valueOf(req.getParameter("INTRO_TIME").trim());
//			try {
//				INTRO_TIME = java.sql.Date.valueOf(req.getParameter("INTRO_TIME").trim());
//			} catch (IllegalArgumentException e) {
//				INTRO_TIME = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}
//			Part pic = req.getPart("AD_PIC");
//		    InputStream picIs = pic.getInputStream();
//		    byte[] AD_PIC = new byte[picIs.available()];
//		    picIs.read(AD_PIC);
//		    System.out.println("Pic");
			Blob REST_PIC = null;
			
			
			RestaurantVo restaurantVO = new RestaurantVo();
			restaurantVO.setREST_ID(REST_ID);
			restaurantVO.setREST_NAME(REST_NAME);
			restaurantVO.setREST_PIC(REST_PIC);
			restaurantVO.setREST_FLOOR(REST_FLOOR);
			restaurantVO.setREST_TIME(REST_TIME);
			restaurantVO.setREST_INTRO(REST_INTRO);
			restaurantVO.setINTRO_TIME(INTRO_TIME);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("restaurantVo", restaurantVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/admin/restaurant.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			

			/*************************** 2.開始修改資料 *****************************************/
			RestaurantService restaurantService = new RestaurantService();
			System.out.println("IN DB");
			restaurantVO = restaurantService.update(REST_ID,REST_NAME, REST_PIC, REST_FLOOR, REST_TIME, REST_INTRO, INTRO_TIME);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("restaurantADVO", restaurantVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/admin/restaurant.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
		/***************************************************************************************************************************************************/
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer REST_ID = Integer.valueOf(req.getParameter("REST_ID"));

			/*************************** 2.開始刪除資料 ***************************************/
			RestaurantADService restaurantADService = new RestaurantADService();
			restaurantADService.deleteAD(REST_ID);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/admin/restaurantAd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

/***************************************************************************************************************************************************/		
		
//		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
//			System.out.println("abc");
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//			String REST_NAME = req.getParameter("REST_NAME");
//			String REST_NAMEReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
//			if (REST_NAME == null || REST_NAME.trim().length() == 0) {
//				errorMsgs.add("餐廳名稱: 請勿空白");
//				System.out.println("餐廳名稱: 請勿空白");
//			} else if (!REST_NAME.trim().matches(REST_NAMEReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("餐廳名稱長度必需在2到100字之間");
//				System.out.println("餐廳名稱長度必需在2到100字之間");
//			}
//			String REST_FLOOR = req.getParameter("REST_FLOOR").trim();
//			if (REST_FLOOR == null || REST_FLOOR.trim().length() == 0) {
//				errorMsgs.add("樓層請勿空白");
//				System.out.println("樓層請勿空白");
//			}
//
//			String REST_TIME = req.getParameter("REST_TIME").trim();
//			if (REST_TIME == null || REST_TIME.trim().length() == 0) {
//				errorMsgs.add("日期內容請填入xx:xx-xx:xx");
//				System.out.println("日期內容請填入xx:xx-xx:xx");
//			}
//			String REST_INTRO = req.getParameter("REST_INTRO").trim();
//
//			java.sql.Date INTRO_TIME = null;
//			try {
//				INTRO_TIME = java.sql.Date.valueOf(req.getParameter("INTRO_TIME").trim());
//			} catch (IllegalArgumentException e) {
//				INTRO_TIME = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}
////			Part pic = req.getPart("AD_PIC");
////		    InputStream picIs = pic.getInputStream();
////		    byte[] AD_PIC = new byte[picIs.available()];
////		    picIs.read(AD_PIC);
////		    System.out.println("Pic");
//			Blob REST_PIC = null;
//
//
//
//			RestaurantVo restaurantVO = new RestaurantVo();
//			restaurantVO.setREST_NAME(REST_NAME);
//			restaurantVO.setREST_PIC(REST_PIC);
//			restaurantVO.setREST_FLOOR(REST_FLOOR);
//			restaurantVO.setREST_TIME(REST_TIME);
//			restaurantVO.setREST_INTRO(REST_INTRO);
//			restaurantVO.setINTRO_TIME(INTRO_TIME);
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("restaurantVO", restaurantVO); // 含有輸入格式錯誤的empVO物件,也存入req
//				RequestDispatcher failureView = req.getRequestDispatcher("/admin/restaurant.jsp");
//				failureView.forward(req, res);
//				System.out.println("there were errors");
//				return;
//			}
//
//			/*************************** 2.開始新增資料 ***************************************/
//			RestaurantService restaurantService = new RestaurantService();
//			System.out.println("IN DB");
//			restaurantVO = restaurantService.addR(REST_NAME, REST_PIC, REST_FLOOR, REST_TIME, REST_INTRO, INTRO_TIME);
//
//			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			String url = "/admin/restaurant.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//			successView.forward(req, res);
//			System.out.println("SUCCESS");
//		}
	}
}
