package agjs.service.impl.announcement;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agjs.bean.announcement.AnnouncementCountVo;
import agjs.bean.announcement.AnnouncementFilterVo;
import agjs.bean.announcement.AnnouncementPo;
import agjs.dao.announcement.AnnouncementDao;
import agjs.service.announcement.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
	@Autowired
	private AnnouncementDao announcementDao;
	
	@Override
	public List<AnnouncementPo> searchKeyword(AnnouncementFilterVo announcementFilterVo) {
		System.out.println("-------------Service Start-------------");
		System.out.println("關鍵字: " + announcementFilterVo.getKeyword());
		List<AnnouncementPo> anmPoList = new ArrayList<AnnouncementPo>();
		if(announcementFilterVo.getKeyword().trim() != "") {
			anmPoList = announcementDao.searchKeyword(announcementFilterVo.getKeyword());
		}
		System.out.println(anmPoList);
		System.out.println("---------------Service End---------------");
		return anmPoList;
	}

	@Override
	public AnnouncementPo insertAnm(AnnouncementPo announcementPo) {
		Date startDate = announcementPo.getAnmStartDate();
		Date endDate = announcementPo.getAnmEndDate();
		LocalDate today = LocalDate.now();
		if(announcementPo.getAnmTitle().trim() == "" || announcementPo.getAnmTitle() == null) {
			System.out.println("請輸入公告標題");
			return null;
		}
		
		if(announcementPo.getAnmContent() == "") {
			System.out.println("請輸入公告內文");
			return null;
		}
		
		if(startDate == null) {
			System.out.println("請選擇公告日期");
			return null;
		}
		
		if(endDate == null) {
			System.out.println("請選擇下架日期");
			return null;
		}
		
		if(startDate.equals(endDate)) {
			System.out.println("下架日期不可與公告日期相同");
			return null;
		}
		
		String startDateString = startDate.toString();
		String todayString = today.toString();
		if(startDateString.equals(todayString)) {
			announcementPo.setAnmStatus("已上架");
		}
		else {
			announcementPo.setAnmStatus("待上架");
		}

		String endDateString = endDate.toString();
		if(endDateString.equals("1970-01-01")) {
			announcementPo.setAnmEndDate(null);
		}
		
		announcementDao.insertAnm(announcementPo);
		return announcementPo;
	}

	@Override
	public AnnouncementPo updateAnm(AnnouncementPo announcementPo) {
		Date startDate = announcementPo.getAnmStartDate();
		Date endDate = announcementPo.getAnmEndDate();
		LocalDate today = LocalDate.now();
		if(announcementPo.getAnmTitle().trim() == "" || announcementPo.getAnmTitle() == null) {
			System.out.println("請輸入公告標題");
		}
		
		if(announcementPo.getAnmContent() == "") {
			System.out.println("請輸入公告內文");
		}
		
		if(startDate == null) {
			System.out.println("請選擇公告日期");
		}
		
		if(endDate == null) {
			System.out.println("請選擇下架日期");
		}
		
		if(startDate.equals(endDate)) {
			System.out.println("下架日期不可與公告日期相同");
		}
		else if (startDate.after(endDate)) {
			System.out.println("下架日期不可早於公告日期");
		}
		
		String startDateString = startDate.toString();
		String todayString = today.toString();
		if(startDateString.equals(todayString)) {
			announcementPo.setAnmStatus("已上架");
		}
		else {
			announcementPo.setAnmStatus("待上架");
		}

		String endDateString = endDate.toString();
		if(endDateString.equals("1970/1/1")) {
			announcementPo.setAnmEndDate(null);
		}
		
		announcementDao.updateAnm(announcementPo);
		return announcementPo;
	}

	@Override
	public List<AnnouncementPo> delete(AnnouncementPo announcementPo) {
		List<AnnouncementPo> anmPoList = announcementDao.delete(announcementPo);
		return anmPoList;
	}

	@Override
	public List<AnnouncementPo> getAnmInfo(AnnouncementPo announcementPo) {
		List<AnnouncementPo> anmPoList = null;
		Date endDate = announcementPo.getAnmEndDate();
		String endDateString = endDate.toString();
		if(endDateString.equals("1970/1/1")) {
			announcementPo.setAnmEndDate(null);
		}
		anmPoList = announcementDao.getAnmInfo(announcementPo);
		return anmPoList;
	}

	@Override
	public List<AnnouncementPo> allAnm() {
		List<AnnouncementPo> anmPoList = announcementDao.allAnm();
		return anmPoList;
	}

	@Override
	public List<AnnouncementPo> filter(AnnouncementFilterVo announcementFilterVo) {
		announcementDao.filter(announcementFilterVo);
		List<AnnouncementPo> anmPoList = announcementDao.filter(announcementFilterVo);
		System.out.println(anmPoList);
		return anmPoList;
	}

	@Override
	public List<AnnouncementPo> publishedAnm(AnnouncementCountVo announcementCountVo) {
		System.out.println("-------------Service Start-------------");
		List<AnnouncementPo> anmPoList = announcementDao.publishedAnm(announcementCountVo);
		System.out.println("-------------Service End-------------");
		System.out.println(anmPoList);
		return anmPoList;
	}
}
