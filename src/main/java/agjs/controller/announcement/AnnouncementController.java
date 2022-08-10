package agjs.controller.announcement;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import agjs.bean.announcement.AnnouncementCountVo;
import agjs.bean.announcement.AnnouncementFilterVo;
import agjs.bean.announcement.AnnouncementPo;
import agjs.bean.announcement.AnnouncementVo;
import agjs.service.announcement.AnnouncementService;

@RestController
@RequestMapping(path = {"/admin/announcement", "/main/announcement"})
public class AnnouncementController {
	@Autowired
	private AnnouncementService announcementService;
	
	@GetMapping("/all")
	public List<AnnouncementPo> allAnm() {
		return announcementService.allAnm();
	}
	
	@PostMapping("/keyword")
	public List<AnnouncementPo> searchKeyword(@RequestBody AnnouncementFilterVo announcementFilterVo) {
		return announcementService.searchKeyword(announcementFilterVo);
	}
	
	@PutMapping("/insert")
	public AnnouncementVo insertAnm(@RequestBody AnnouncementVo announcementVo) {
		return announcementService.insertAnm(announcementVo);
	}
	
	@PostMapping("/searchAnm")
	public List<AnnouncementPo> getAnmInfo(@RequestBody AnnouncementVo announcementVo){
		return announcementService.getAnmInfo(announcementVo);
	}
	
	@PostMapping("/filter")
	public List<AnnouncementPo> filter(@RequestBody AnnouncementFilterVo announcementFilterVo){
		return announcementService.filter(announcementFilterVo);
	}
	
	@DeleteMapping("/delete")
	public List<AnnouncementPo> delete(@RequestBody AnnouncementPo announcementPo){
		return announcementService.delete(announcementPo);
	}
	
	@PatchMapping("/update")
	public AnnouncementPo updateAnm(@RequestBody AnnouncementVo announcementVo){
		return announcementService.updateAnm(announcementVo);
	}
	
	@PostMapping("/published")
	public List<AnnouncementPo> publishedAnm(@RequestBody AnnouncementCountVo announcementCountVo) {
		return announcementService.publishedAnm(announcementCountVo);
	}

}
