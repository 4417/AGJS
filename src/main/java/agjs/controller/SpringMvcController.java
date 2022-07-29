package agjs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import agjs.bean.RoomUsedRecordPo;
import agjs.bean.UserPo;
import agjs.service.SpringMvcService;

@RestController
@RequestMapping("/")
public class SpringMvcController {

	@Autowired
	private SpringMvcService springMvcService;
	private Gson gson;

	public SpringMvcController() {

		System.out.println("SpingMvcController init");
	}


	@GetMapping("/select")
	public String select() {

		gson = new Gson();
		System.out.println(gson.toJson(springMvcService.getRoomUsedRecordAll()));

		return "index";
	}
	@PostMapping("/select2")
	public List<RoomUsedRecordPo> select2(@RequestBody UserPo user) {
		return springMvcService.getRoomUsedRecordAll();
	}
	

}
