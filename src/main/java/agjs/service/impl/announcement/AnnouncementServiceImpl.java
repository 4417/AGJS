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
	public AnnouncementPo insertAnm(AnnouncementVo announcementVo) {
		AnnouncementPo announcementPo = new AnnouncementPo();
		Integer typeId = announcementTypeDao.getAnmType(announcementVo.getAnmType());
		Date startDate = announcementVo.getAnmStartDate();
		Date endDate = announcementVo.getAnmEndDate();
		LocalDate today = LocalDate.now();
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
		
		if(startDate.equals(endDate)) {
			System.out.println("下架日期不可與公告日期相同");
			return null;
		}
		
		String startDateString = startDate.toString();
		String todayString = today.toString();
		if(startDateString.equals(todayString)) {
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
		return announcementPo;
	}

	@Override
	@Transactional
	public AnnouncementPo updateAnm(AnnouncementVo announcementVo) {
		AnnouncementPo announcementPo = new AnnouncementPo();
		Integer typeId = announcementTypeDao.getAnmType(announcementVo.getAnmType());
		Date startDate = announcementVo.getAnmStartDate();
		Date endDate = announcementVo.getAnmEndDate();
		LocalDate today = LocalDate.now();
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
		
		if(startDate.equals(endDate)) {
			System.out.println("下架日期不可與公告日期相同");
		}
		else if (startDate.after(endDate)) {
			System.out.println("下架日期不可早於公告日期");
		}
		
		String startDateString = startDate.toString();
		String todayString = today.toString();
		if(startDateString.equals(todayString)) {
			announcementVo.setAnmStatus("已上架");
		}
		else {
			announcementVo.setAnmStatus("待上架");
		}

		String endDateString = endDate.toString();
		if(endDateString.equals("1970/1/1")) {
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
		Integer typeId = announcementTypeDao.getAnmType(announcementVo.getAnmType());
		Date endDate = announcementVo.getAnmEndDate();
		String endDateString = endDate.toString();
		if(endDateString.equals("1970/1/1")) {
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
		System.out.println("-------------Service Start-------------");
		List<AnnouncementPo> anmPoList = announcementDao.publishedAnm(announcementCountVo);
		System.out.println("-------------Service End-------------");
		System.out.println(anmPoList);
		return anmPoList;
	}
}
