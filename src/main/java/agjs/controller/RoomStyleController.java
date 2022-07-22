package agjs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import agjs.bean.RoomStylePo;
import agjs.service.impl.RoomStyleServiceIm;

@RestController
public class RoomStyleController {

	Gson _gson = new Gson();

	private final RoomStyleServiceIm service;

	@Autowired
	public RoomStyleController(RoomStyleServiceIm service) {
		this.service = service;

	}

//	@CrOossOrigin
	@GetMapping("/roomStyle")
	public List<RoomStylePo> Hello(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("roomStyle");
//		doOptions(request, response);
		return service.getAll();
	}
	
	/*
	 * 誇域
	 */

	protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
//			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		// 重要 給跨域請求呼叫
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "*");
		response.addHeader("Access-Control-Max-Age", "86400");

	}
}
