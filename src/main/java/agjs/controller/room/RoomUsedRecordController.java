package agjs.controller.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.room.RoomUsedRecordVo;
import agjs.service.room.RoomUsedRecordService;

@RestController
public class RoomUsedRecordController {
	@Autowired
	private RoomUsedRecordService service;
	

	@GetMapping("/roomUsedRecord")
	public List<RoomUsedRecordVo> getAll() {
		System.out.println("roomStyle");
		return service.getAll();
	}
	
	@PostMapping(value = "/roomUsedRecord", consumes = MediaType.APPLICATION_JSON_VALUE)
	public RoomUsedRecordVo select(@RequestBody RoomUsedRecordVo recordModel) {
		System.out.println("select");
		System.out.println("recordModel"+recordModel);
		System.out.println("roomName="+recordModel.getRoomName());
		System.out.println("source"+recordModel.getSource());
		
//		RoomUsedRecordPo recordPo = new RoomUsedRecordPo();
//		recordPo.setStartDate(recordModel.getStartDate());
//		recordPo.setSource(recordModel.getSource());
//		RoomStylePo roomStylePo = new RoomStylePo();
//		roomStylePo.setRoomName(recordModel.getRoomName());
		
		
		return recordModel;
	}

}
