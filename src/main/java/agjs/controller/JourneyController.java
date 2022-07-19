package agjs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import agjs.model.SpringMvcDao;

@Controller
@RequestMapping("/journey/**")
public class JourneyController {
	
	@Autowired
	private SpringMvcDao dao;
	
	public JourneyController() {
		System.out.println("JourneyController init");
	}
	
	@GetMapping("/select")
	public void select(HttpServletRequest request, HttpServletResponse response){
		
		System.out.println("JourneyController select()");
		Gson gson = new Gson();
		System.out.println(gson.toJson(dao.selectRoomUsedRecord()));
		
		
	}

}
