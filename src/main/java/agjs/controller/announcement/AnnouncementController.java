package agjs.controller.announcement;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import agjs.bean.announcement.AnnouncementPo;
import agjs.dao.announcement.AnnouncementDao;
import agjs.dao.impl.announcement.AnnouncementDaoImpl;
import agjs.service.announcement.AnnouncementService;
import agjs.service.impl.announcement.AnnouncementServiceImpl;

@RestController
@RequestMapping("/admin/announcement")
public class AnnouncementController {
	@Autowired
	private AnnouncementService announcementService;
	
	@GetMapping("/all")
	public List<AnnouncementPo> allAnm() {
		return announcementService.allAnm();
	}
	
	@PostMapping("/keyword")
	public List<AnnouncementPo> selectKeyword(String keyword) {
		return announcementService.selectKeyword(keyword);
	}
	
	@PutMapping("/insert")
	public AnnouncementPo insertAnm(@RequestBody AnnouncementPo announcementPo) {
		announcementService.insertAnm(announcementPo);
		return announcementPo;
	}
	
	@PostMapping("/searchAnm")
	public List<AnnouncementPo> getAnmInfo(@RequestBody AnnouncementPo announcementPo){
		return announcementService.getAnmInfo(announcementPo);
	}
	
	@DeleteMapping("/delete")
	public List<AnnouncementPo> delete(@RequestBody AnnouncementPo announcementPo){
		return announcementService.delete(announcementPo);
	}

}
