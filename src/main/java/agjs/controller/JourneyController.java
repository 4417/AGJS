package agjs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.JourneyTypeVo;
import agjs.service.JourneyTypeService;

@RestController
@RequestMapping(path = { "/main/jrn", "/admin/jrn" })
public class JourneyController {
	
	@Autowired
	private JourneyTypeService journeyTypeService;
	
	
	//回傳行程類型 journeyType
	@GetMapping("/type")
	public List<JourneyTypeVo> getTypes() {
		
		return journeyTypeService.getJourneyType();
	}

}
