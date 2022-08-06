package agjs.controller.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.room.RoomUsedRecordPo;
import agjs.service.room.RoomUsedRecordService;

@RestController
public class RoomUsedRecordController {
	@Autowired
	private RoomUsedRecordService service;

	@GetMapping("/roomUsedRecord")
	public List<RoomUsedRecordPo> getAll() {
		System.out.println("roomStyle");
		return service.getAll();
	}

}
