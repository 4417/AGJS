package agjs.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agjs.bean.AnnouncementPo;
import agjs.dao.AnnouncementDao;
import agjs.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
	@Autowired
	private AnnouncementDao announcementDao;
	
	@Override
	public List<AnnouncementPo> selectKeyword(String keyword) {
		System.out.println("here is Service");
		if(keyword.trim() == "") {
			System.out.println("請輸入關鍵字");
			return null;
		}
		System.out.println("關鍵字: " + keyword);
		List<AnnouncementPo> anmPoList = null;
		anmPoList = announcementDao.selectKeyword(keyword);
		if(anmPoList.size() < 1) {
			System.out.println("沒有資料");
		}
		System.out.println("我是SERVICE: " + anmPoList);
		return anmPoList;
	}

	@Override
	public List<AnnouncementPo> selectStartDate(AnnouncementPo announcementPo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnnouncementPo> selectEndDate(AnnouncementPo announcementPo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnnouncementPo> selectType(AnnouncementPo announcementPo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnnouncementPo insertAnm(AnnouncementPo announcementPo) {
		System.out.println("here is Service");
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
		if(endDateString.equals("1970-01-01")) {
			announcementPo.setAnmEndDate(null);
		}
		
		System.out.println(announcementPo);
		announcementDao.insertAnm(announcementPo);
		return announcementPo;
	}

	@Override
	public AnnouncementPo updateAnm(Integer anmId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnnouncementPo> deleteAnm(Integer anmId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnnouncementPo> getAnmInfo(AnnouncementPo announcementPo) {
		System.out.println("here is Service");
		List<AnnouncementPo> anmPoList = null;
		Date endDate = announcementPo.getAnmEndDate();
		String endDateString = endDate.toString();
		if(endDateString.equals("1970-01-01")) {
			announcementPo.setAnmEndDate(null);
		}
		anmPoList = announcementDao.getAnmInfo(announcementPo);
		System.out.println("我是SERVICE: " + anmPoList);
		return anmPoList;
	}

}
