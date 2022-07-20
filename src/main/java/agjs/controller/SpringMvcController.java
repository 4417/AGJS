package agjs.controller;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import agjs.service.SpringMvcService;

@Controller
@RequestMapping("/")
//@WebServlet("/")
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

}
