package agjs.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import agjs.bean.AnnouncementPo;
import agjs.dao.AnnouncementDao;
import agjs.dao.impl.AnnouncementDaoImpl;
import agjs.service.AnnouncementService;
import agjs.service.impl.AnnouncementServiceImpl;

@WebServlet("/admin/announcement")
public class AnnouncementController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("in Anmcontroller");
		
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//		AnnouncementDao announcementDao = new AnnouncementDaoImpl();
	
		
		try {
//			Gson gson = new Gson();
//			JsonObject respObj = new JsonObject();
			AnnouncementService announcementService = new AnnouncementServiceImpl();
			String keyword = request.getParameter("keyword");
			announcementService.selectKeyword(keyword);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
