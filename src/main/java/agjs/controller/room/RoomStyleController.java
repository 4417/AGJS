package agjs.controller.room;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.room.RoomInformationFacilitiesPo;
import agjs.bean.room.RoomStyleVo;
import agjs.bean.room.RoomStylePo;
import agjs.service.room.RoomStyleService;

@RestController
@RequestMapping("/admin")
public class RoomStyleController {

	@Autowired
	private RoomStyleService service;

	// 回傳全部
	@GetMapping("/roomStyle")
	public List<RoomStylePo> getAll(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("roomStyle");
		doOptions(request, response);
		return service.getAll();
	}

	// 根據id回傳roomStyle
	@GetMapping("/roomStyle/{id}")
	public RoomStyleVo getRoomStyleById(@PathVariable Integer id) {
		System.out.println("roomStyleById = " + id);
		RoomStylePo roomStylePo = (RoomStylePo) service.getById(id);
		List<RoomInformationFacilitiesPo> facilities = service.findFacilitiesByRoomStyleId(id);

		RoomStyleVo roomStyleModel = new RoomStyleVo();
		roomStyleModel.setRoomName(roomStylePo.getRoomName());
		roomStyleModel.setRoomDescription(roomStylePo.getRoomDescription());
		roomStyleModel.setRoomQuantity(roomStylePo.getRoomQuantity());
		roomStyleModel.setRoomStyleId(roomStylePo.getRoomStyleId());
		roomStyleModel.setRoomDescription(roomStylePo.getRoomDescription());
		roomStyleModel.setOrderRoomPrice(roomStylePo.getOrderRoomPrice());
		roomStyleModel.setRoomType(roomStylePo.getRoomType());
		roomStyleModel.setBedType(roomStylePo.getBedType());

		List<Integer> roomFacilitiesIdList = new ArrayList<Integer>();
		for (RoomInformationFacilitiesPo po : facilities) {
			roomFacilitiesIdList.add(po.getId().getRoomFacilitiesId());
		}
		roomStyleModel.setRoomFacilitiesIdList(roomFacilitiesIdList);
		return roomStyleModel;
	}

	// 新增roomStyle資料
	@PostMapping(value = "/roomStyle", consumes = MediaType.APPLICATION_JSON_VALUE)
	public RoomStylePo addRoomStyle(@RequestBody RoomStyleVo roomStyleModel) {

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

		// RoomStylemodel轉變成RoomStylePo
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

	// 刪除房間資料
	@DeleteMapping(value = "/roomStyle", consumes = MediaType.APPLICATION_JSON_VALUE)
	public RoomStylePo deleteRoomStyles(@RequestBody Integer[] roomStyleIds) {
		System.out.println("Delete");
		for (Integer roomStyleId : roomStyleIds) {
			System.out.println(roomStyleId);
		}
		// 將收到的id丟到service層
		service.delete(roomStyleIds);

		return null;
	}

	// 修改房間資料
	@PostMapping(value = "/roomStyle/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public RoomStylePo updateRoomStyle(@RequestBody RoomStyleVo roomStyleModel) {
		System.out.println("Update");
		System.out.println("roomStyleModel:" + roomStyleModel);
		System.out.println(roomStyleModel.getRoomStyleId());
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
		// 將傳進來的值轉換成roomStylePo需要的物件
		RoomStylePo roomStylePo = new RoomStylePo();
		roomStylePo.setRoomStyleId(roomStyleModel.getRoomStyleId());
		roomStylePo.setRoomName(roomStyleModel.getRoomName());
		roomStylePo.setBedType(roomStyleModel.getBedType());
		roomStylePo.setRoomDescription(roomStyleModel.getRoomDescription());
		roomStylePo.setRoomType(roomStyleModel.getRoomType());
		roomStylePo.setOrderRoomPrice(roomStyleModel.getOrderRoomPrice());
		roomStylePo.setRoomQuantity(roomStyleModel.getRoomQuantity());

		service.updateRoomStyle(roomStylePo, roomStyleModel.getRoomFacilitiesIdList());
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
