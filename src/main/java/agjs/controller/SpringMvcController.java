<<<<<<< HEAD
//package agjs.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import agjs.bean.RoomUsedRecordPo;
//import agjs.bean.UserPo;
//import agjs.service.SpringMvcService;
//
//@RestController
//@RequestMapping("/")
//public class SpringMvcController {
//
//	@Autowired
//	private SpringMvcService springMvcService;
//	@GetMapping("/select")
//	public List<RoomUsedRecordPo> select() {
//		return springMvcService.getRoomUsedRecordAll();
//	}
//	@PostMapping("/select2")
//	public List<RoomUsedRecordPo> select2(@RequestBody UserPo user) {
//		return springMvcService.getRoomUsedRecordAll();
//	}
//	
//
//}
=======
package agjs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.room.RoomUsedRecordPo;
import agjs.bean.user.UserPo;
import agjs.service.SpringMvcService;

@RestController
@RequestMapping("/")
public class SpringMvcController {

	@Autowired
	private SpringMvcService springMvcService;

	@GetMapping("/select")
	public List<RoomUsedRecordPo> select() {
		return springMvcService.getRoomUsedRecordAll();
	}

	@PostMapping("/select2")
	public List<RoomUsedRecordPo> select2(@RequestBody UserPo user) {
		return springMvcService.getRoomUsedRecordAll();
	}

}
>>>>>>> dev
