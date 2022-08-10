package agjs.controller.room;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.room.RoomVo_2;
import agjs.bean.user.UserPo;
import agjs.service.room.RoomService_2;

@RestController
@RequestMapping("/main/order")
public class RoomController_2 {
	
	@Autowired
	private RoomService_2 roomService;
	
	//查詢是否有符合數量的房型空房
	@PostMapping("/search/room_quantity")
	public String checkRoom(@RequestBody RoomVo_2 vo,HttpSession session) {
		UserPo user= (UserPo) session.getAttribute("login");
		System.out.println(vo.getOrderStartDate());
		System.out.println(vo.getOrderEndDate());
		
		RoomVo_2 newVo=roomService.selectFromDR(vo, user);
		return newVo.getMsg();
	}

}
