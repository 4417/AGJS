package agjs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.RoomStylePo;
import agjs.controller.model.RoomStyleModel;
import agjs.service.RoomStyleService;

@RestController
public class RoomStyleController {

	@Autowired
	private RoomStyleService service;

//	
//	public RoomStyleController(RoomStyleServiceImpl service) {
//		this.service = service;
//
//	}

	@GetMapping("/roomStyle")
	public List<RoomStylePo> getAll(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("roomStyle");
		doOptions(request, response);
		return service.getAll();
	}

	@PostMapping(value = "/roomStyle", consumes = MediaType.APPLICATION_JSON_VALUE)
	public RoomStylePo addRoomStyle(@RequestBody RoomStyleModel roomStyleModel) {

		System.out.println("Post");
		System.out.println("roomStyleModel:" + roomStyleModel);
		System.out.println(roomStyleModel.getRoomName());
		System.out.println(roomStyleModel.getBedType());
		System.out.println(roomStyleModel.getRoomDescription());
		System.out.println(roomStyleModel.getRoomType());
		System.out.println(roomStyleModel.getOrderRoomPrice());
		System.out.println(roomStyleModel.getRoomQuantity());
		System.out.println(roomStyleModel.getRoomFacilitiesIdList());
		for (Integer facilitiesId : roomStyleModel.getRoomFacilitiesIdList()) {
			System.out.println(facilitiesId);
		}
		
		//TODO 驗證輸入的值
		//RoomStylemodel轉變成RoomStylePo 
		RoomStylePo roomStylePo = new RoomStylePo();
		roomStylePo.setRoomName(roomStyleModel.getRoomName());
		roomStylePo.setBedType(roomStyleModel.getBedType());
		roomStylePo.setRoomDescription(roomStyleModel.getRoomDescription());
		roomStylePo.setRoomType(roomStyleModel.getRoomType());
		roomStylePo.setOrderRoomPrice(roomStyleModel.getOrderRoomPrice());
		roomStylePo.setRoomQuantity(roomStyleModel.getRoomQuantity());
		
		
		Integer id = service.addRoomStyle(roomStylePo, roomStyleModel.getRoomFacilitiesIdList());
//		roomStylePo = service.getById(id);
		return roomStylePo;
	}

	/*
	 * 跨域
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
