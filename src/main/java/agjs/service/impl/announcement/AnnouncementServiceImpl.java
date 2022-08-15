package agjs.service.impl.announcement;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.announcement.AnnouncementCountVo;
import agjs.bean.announcement.AnnouncementFilterVo;
import agjs.bean.announcement.AnnouncementPo;
import agjs.bean.announcement.AnnouncementVo;
import agjs.dao.announcement.AnnouncementDao;
import agjs.dao.announcement.AnnouncementTypeDao;
import agjs.service.announcement.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
	@Autowired
	private AnnouncementDao announcementDao;
	@Autowired
	private AnnouncementTypeDao announcementTypeDao;
	
	@Override
	public List<AnnouncementPo> searchKeyword(AnnouncementFilterVo announcementFilterVo) {
		List<AnnouncementPo> anmPoList = new ArrayList<AnnouncementPo>();
		if(announcementFilterVo.getKeyword().trim() != "") {
			anmPoList = announcementDao.searchKeyword(announcementFilterVo.getKeyword());
		}
		return anmPoList;
	}

	@Override
	public AnnouncementVo insertAnm(AnnouncementVo announcementVo) {
		AnnouncementPo announcementPo = new AnnouncementPo();
		Integer typeId = announcementTypeDao.getAnmTypeId(announcementVo.getAnmType());
		Date startDate = announcementVo.getAnmStartDate();
		Date endDate = announcementVo.getAnmEndDate();
		java.util.Date today = new java.util.Date();
//		LocalDate today = LocalDate.now();
		if(announcementVo.getAnmTitle().trim() == "" || announcementVo.getAnmTitle() == null) {
			System.out.println("請輸入公告標題");
			return null;
		}
		
		if(announcementVo.getAnmContent() == "") {
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
		
		String startDateString = startDate.toString();
		String todayString = today.toString();
		if(startDateString.equals(todayString) || startDate.before(today)) {
			announcementVo.setAnmStatus("已上架");
		}
		else {
			announcementVo.setAnmStatus("待上架");
		}

		String endDateString = endDate.toString();
		if(endDateString.equals("1970-01-01")) {
			announcementVo.setAnmEndDate(null);
		}
		if(endDateString.equals(todayString)) {
			announcementVo.setAnmStatus("已下架");
		}
		
		announcementPo.setAdministratorId(1);
		announcementPo.setAnmTypeId(typeId);
		announcementPo.setAnmTitle(announcementVo.getAnmTitle());
		announcementPo.setAnmContent(announcementVo.getAnmContent());
		announcementPo.setAnmStartDate(announcementVo.getAnmStartDate());
		announcementPo.setAnmEndDate(announcementVo.getAnmEndDate());
		announcementPo.setAnmOrderId(announcementVo.getAnmOrderId());
		announcementPo.setAnmStatus(announcementVo.getAnmStatus());
		
		announcementDao.insertAnm(announcementPo);
		return announcementVo;
	}

	@Override
	@Transactional
	public AnnouncementPo updateAnm(AnnouncementVo announcementVo) {
		AnnouncementPo announcementPo = new AnnouncementPo();
		Integer typeId = announcementTypeDao.getAnmTypeId(announcementVo.getAnmType());
		Date startDate = announcementVo.getAnmStartDate();
		Date endDate = announcementVo.getAnmEndDate();
//		LocalDate today = LocalDate.now();
		java.util.Date today = new java.util.Date();    
		System.out.println("today:"+today);   
		if(announcementVo.getAnmTitle().trim() == "" || announcementVo.getAnmTitle() == null) {
			System.out.println("請輸入公告標題");
		}
		
		if(announcementVo.getAnmContent() == "") {
			System.out.println("請輸入公告內文");
		}
		
		if(startDate == null) {
			System.out.println("請選擇公告日期");
		}
		
		if(endDate == null) {
			System.out.println("請選擇下架日期");
		}
		
		String startDateString = startDate.toString();
		String todayString = today.toString();
		if(startDateString.equals(todayString) || startDate.before(today)) {
			announcementVo.setAnmStatus("已上架");
		}
		else {
			announcementVo.setAnmStatus("待上架");
		}

		String endDateString = endDate.toString();
		if(endDateString.equals("1970-01-01")) {
			announcementVo.setAnmEndDate(null);
		}
		if(endDateString.equals(todayString)) {
			announcementVo.setAnmStatus("已下架");
		}
		
		announcementPo.setAnmId(announcementVo.getAnmId());
		announcementPo.setAnmTypeId(typeId);
		announcementPo.setAnmTitle(announcementVo.getAnmTitle());
		announcementPo.setAnmContent(announcementVo.getAnmContent());
		announcementPo.setAnmStartDate(announcementVo.getAnmStartDate());
		announcementPo.setAnmEndDate(announcementVo.getAnmEndDate());
		announcementPo.setAnmOrderId(announcementVo.getAnmOrderId());
		announcementPo.setAnmStatus(announcementVo.getAnmStatus());
		System.out.println(announcementPo);
		announcementDao.updateAnm(announcementPo);
		return announcementPo;
	}

	@Override
	@Transactional
	public List<AnnouncementPo> delete(AnnouncementPo announcementPo) {
		List<AnnouncementPo> anmPoList = announcementDao.delete(announcementPo);
		return anmPoList;
	}

	@Override
	public List<AnnouncementPo> getAnmInfo(AnnouncementVo announcementVo) {
		List<AnnouncementPo> anmPoList = null;
		AnnouncementPo announcementPo = new AnnouncementPo();
		Integer typeId = announcementTypeDao.getAnmTypeId(announcementVo.getAnmType());
		Date endDate = announcementVo.getAnmEndDate();
		String endDateString = endDate.toString();
		if(endDateString.equals("1970-01-01")) {
			announcementVo.setAnmEndDate(null);
		}
		
//		announcementPo.setAnmId(announcementVo.getAnmId());
		announcementPo.setAnmTypeId(typeId);
		announcementPo.setAnmTitle(announcementVo.getAnmTitle());
//		announcementPo.setAnmContent(announcementVo.getAnmContent());
		announcementPo.setAnmStartDate(announcementVo.getAnmStartDate());
//		announcementPo.setAnmEndDate(announcementVo.getAnmEndDate());
//		announcementPo.setAnmOrderId(announcementVo.getAnmOrderId());
//		announcementPo.setAnmStatus(announcementVo.getAnmStatus());
		
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
		List<AnnouncementPo> anmPoList = announcementDao.publishedAnm(announcementCountVo);
		return anmPoList;
	}
}
