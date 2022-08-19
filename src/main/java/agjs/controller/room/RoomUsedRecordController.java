package agjs.controller.room;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.room.RoomUsedRecordPo;
import agjs.bean.room.RoomUsedRecordVo;
import agjs.service.room.RoomUsedRecordService;

@RestController
@RequestMapping("/admin")
public class RoomUsedRecordController {
	@Autowired
	private RoomUsedRecordService service;

	@GetMapping("/roomUsedRecord")
	public List<RoomUsedRecordVo> getAll() {
		System.out.println("roomStyle");
		return service.getAll();
	}

	@PostMapping(value = "/roomUsedRecord", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> select(@RequestBody RoomUsedRecordVo recordModel) {
		System.out.println("-----------------roomRecordSelect----------------------");
		System.out.println("recordModel=" + recordModel);
		System.out.println("roomName=" + recordModel.getRoomName());
		System.out.println("StartDate=" + recordModel.getOrderStartDate());

		RoomUsedRecordVo recordVo = new RoomUsedRecordVo();
		recordVo.setRoomName(recordModel.getRoomName());
		recordVo.setOrderStartDate(recordModel.getOrderStartDate());
		return service.select(recordVo);
	}
	
	@PostMapping(value = "/roomUsedRecord/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public RoomUsedRecordPo update(@RequestBody RoomUsedRecordVo recordModel) {
		System.out.println("-----------------roomRecordUpdate----------------------");
		System.out.println("recordModel=" + recordModel);
		System.out.println("roomId:"+recordModel.getRoomId());
		System.out.println("startDate:"+recordModel.getOrderStartDate());
		System.out.println("endDate"+recordModel.getOrderEndDate());
		
		RoomUsedRecordPo recordVo = new RoomUsedRecordPo();
		recordVo.setRoomId(recordModel.getRoomId());
		recordVo.setStartDate(recordModel.getOrderStartDate());
		recordVo.setEndDate(recordModel.getOrderEndDate());
		recordVo.setSource(1);//給預設值(已棄用)
		
		return service.update(recordVo);
	}

}
