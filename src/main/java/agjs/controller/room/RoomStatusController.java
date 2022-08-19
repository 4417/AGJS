package agjs.controller.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.room.RoomCardVo;
import agjs.bean.room.RoomStatusVo;
import agjs.bean.room.StartBookingVo;
import agjs.service.room.RoomStatusService;
import agjs.service.user.RegisterMailService;

/**
 * 空房 房間狀態查詢 controller
 */
@RestController
@RequestMapping(path = { "/main/roomstatus", "/admin/roomstatus" })
public class RoomStatusController {

	@Autowired
	RoomStatusService roomStatusService;

	// 日期搜尋 空房的房型ID
	@PostMapping("/search/empty.daterange")
	public RoomStatusVo searchEmpltyRoomIDByDateRange(@RequestBody RoomStatusVo roomStatusVo) {

		System.out.println("日期搜尋 空房的房型ID");
		System.out.println(roomStatusVo.getStartDate());
		System.out.println(roomStatusVo.getEndDate());

		return roomStatusService.searchEmptyRoomByDateRange(roomStatusVo);
	}

	// 空房房型ID搜尋 房型
	@PostMapping("/search/roomcard.styleid")
	public List<RoomCardVo> searchJourneyItemBySohId(@RequestBody StartBookingVo startBookingVo) {

		System.out.println("空房房型ID搜尋 可訂房型資料");
		System.out.println(startBookingVo);
		for (int i = 0; i < startBookingVo.getStyleIdStrings().length; i++) {
			System.out.println(startBookingVo.getStyleIdStrings()[i]);
			System.out.println(startBookingVo.getStartDate());
		}

		return roomStatusService.searchRoomCardByEmptyRoomTypeId(startBookingVo);
	}

}
