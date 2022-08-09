package agjs.controller.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.room.RoomVo_2;
import agjs.dao.room.RoomDao_2;

@RestController
@RequestMapping("/main/order")
public class RoomController_2 {
	@Autowired
	private RoomDao_2 dao;
	
	//查詢是否有符合數量的房型空房
	@PostMapping("/search/room_quantity")
	public String checkRoom(@RequestBody RoomVo_2 vo) {
		vo.setRoomName("山景標準房");
		System.out.println(vo.getOrderStartDate());
		System.out.println(vo.getOrderEndDate());
		Integer room=dao.selectFromDateAndRoomStyle(vo.getOrderStartDate(), vo.getOrderEndDate(), "海景雅致房");
		System.out.println("房數="+room);
		return "成功傳送";
	}

}
