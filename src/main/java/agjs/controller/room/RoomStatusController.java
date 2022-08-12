package agjs.controller.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.room.RoomCardVo;
import agjs.bean.room.RoomStatusVo;
import agjs.service.room.RoomStatusService;

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
	public List<RoomCardVo>  searchJourneyItemBySohId(@RequestBody String[] styleIdStrings) {

		System.out.println("空房房型ID搜尋 房型");
		for (int i = 0; i < styleIdStrings.length; i++) {
			System.out.println(styleIdStrings[i]);
		}

		return roomStatusService.searchRoomCardByEmptyRoomTypeId(styleIdStrings);
	}

}
