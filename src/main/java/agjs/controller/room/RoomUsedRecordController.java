package agjs.controller.room;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
		System.out.println("-----------------select----------------------");
		System.out.println("recordModel=" + recordModel);
		System.out.println("roomName=" + recordModel.getRoomName());
		System.out.println("StartDate=" + recordModel.getOrderStartDate());

		// 轉換日期
//		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
//		
//		DateTimeFormatter inpuFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate date = LocalDate.parse(ft.format(recordModel.getOrderStartDate()),inpuFormat);
//		System.out.println("轉換時間格式:"+date);
//		

		RoomUsedRecordVo recordVo = new RoomUsedRecordVo();
		recordVo.setRoomName(recordModel.getRoomName());
		recordVo.setOrderStartDate(recordModel.getOrderStartDate());
		service.select(recordVo);
		return recordVo;
	}

}
