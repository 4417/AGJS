//package agjs.controller.restaurant;
//
//import java.io.IOException;
//import java.sql.Blob;
//import java.util.LinkedList;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import agjs.bean.restaurant.RestaurantADVO;
//import agjs.service.restaurant.RestaurantADService;
//
//@WebServlet("/admin/restaurantAd")
//public class RestaurantADServlet extends HttpServlet {
//
//	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
//	}
//
//	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//		System.out.println("11112");
///***************************************************************************************************************************************************/
//		// 來自update.jsp的請求
//		if ("update".equals(action)) { 
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//
//			String adName = req.getParameter("adName");
//			String adNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
//			if (adName == null || adName.trim().length() == 0) {
//				errorMsgs.add("優惠名稱: 請勿空白");
//			} else if (!adName.trim().matches(adNameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("優惠名稱長度必需在2到100字之間");
//			}
//
//			String adIntro = req.getParameter("adIntro").trim();
//			if (adIntro == null || adIntro.trim().length() == 0) {
//				errorMsgs.add("內容請勿空白");
//			}
//
//			java.sql.Date adTime = null;
//			try {
//				adTime = java.sql.Date.valueOf(req.getParameter("adTime").trim());
//			} catch (IllegalArgumentException e) {
//				adTime = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}
//
//			byte[] adPic = null;
//			
//
//			Integer adId = Integer.valueOf(req.getParameter("adId").trim());
//			Integer restId = Integer.valueOf(req.getParameter("restId").trim());
//			
//			RestaurantADVO restaurantADVO = new RestaurantADVO();
//			restaurantADVO.setAdName(adName);
//			restaurantADVO.setAdIntro(adIntro);
//			restaurantADVO.setAdTime(adTime);
//			restaurantADVO.setAdId(adId);
//			restaurantADVO.setRestId(restId);
//			restaurantADVO.setAdPic(adPic);
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("restaurantADVO", restaurantADVO); // 含有輸入格式錯誤的empVO物件,也存入req
//				RequestDispatcher failureView = req.getRequestDispatcher("/emp/update_emp_input.jsp");
//				failureView.forward(req, res);
//				return; // 程式中斷
//			}
//
//			/*************************** 2.開始修改資料 *****************************************/
//			RestaurantADService restaurantADService = new RestaurantADService();
//			restaurantADVO = restaurantADService.updateAd(adId, restId, adName, adPic, adIntro, adTime);
//
//			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("restaurantADVO", restaurantADVO); // 資料庫update成功後,正確的的empVO物件,存入req
//			String url = "/admin/restaurantAd.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//			successView.forward(req, res);
//		}
//		
///***************************************************************************************************************************************************/
//		
//		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
//			System.out.println("abc");
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//			String adName = req.getParameter("adName");
//			String adNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
//			if (adName == null || adName.trim().length() == 0) {
//				errorMsgs.add("優惠名稱: 請勿空白");
//			} else if (!adName.trim().matches(adNameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("優惠名稱長度必需在2到100字之間");
//			}
//			String adIntro = req.getParameter("adIntro").trim();
//			if (adIntro == null || adIntro.trim().length() == 0) {
//				errorMsgs.add("內容請勿空白");
//			}
//
//
//			java.sql.Date adTime = null;
//			try {
//				adTime = java.sql.Date.valueOf(req.getParameter("adTime").trim());
//			} catch (IllegalArgumentException e) {
//				adTime = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}
//			
//			byte[] adPic = null;
//
//			Integer restId = Integer.valueOf(req.getParameter("restId").trim());
//			Integer adId = Integer.valueOf(req.getParameter("adId").trim());
//
//			RestaurantADVO restaurantADVO = new RestaurantADVO();
//			restaurantADVO.setAdName(adName);
//			restaurantADVO.setAdIntro(adIntro);
//			restaurantADVO.setAdTime(adTime);
//			restaurantADVO.setRestId(restId);
//			restaurantADVO.setAdId(adId);
//			restaurantADVO.setAdPic(adPic);
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("restaurantADVO", restaurantADVO); // 含有輸入格式錯誤的empVO物件,也存入req
//				RequestDispatcher failureView = req.getRequestDispatcher("/admin/restaurantAd.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/*************************** 2.開始新增資料 ***************************************/
//			RestaurantADService restaurantADService = new RestaurantADService();
//			restaurantADVO = restaurantADService.addAD(adId, restId, adName, adPic, adIntro, adTime);
//
//			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			String url = "/admin/restaurantAd.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//			successView.forward(req, res);
//		}
///***************************************************************************************************************************************************/
////		if ("delete".equals(action)) { // 來自listAllEmp.jsp
////
////			List<String> errorMsgs = new LinkedList<String>();
////			// Store this set in the request scope, in case we need to
////			// send the ErrorPage view.
////			req.setAttribute("errorMsgs", errorMsgs);
////
////			/*************************** 1.接收請求參數 ***************************************/
////			Integer adId = Integer.valueOf(req.getParameter("adId"));
////
////			/*************************** 2.開始刪除資料 ***************************************/
////			RestaurantADService restaurantADService = new RestaurantADService();
////			restaurantADService.deleteAD(adId);
////
////			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
////			String url = "/admin/restaurantAd.jsp";
////			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
////			successView.forward(req, res);
////		}
//	}
//}
