package agjs.service.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import agjs.bean.AnnouncementPo;
import agjs.dao.AnnouncementDao;
import agjs.dao.UserDao;
import agjs.dao.impl.AnnouncementDaoImpl;
import agjs.service.AnnouncementService;

public class AnnouncementServiceImpl implements AnnouncementService {
	
	private AnnouncementDao announcementDao;
	
	@Override
	public List<AnnouncementPo> selectKeyword(String keyword) {
		System.out.println("here is Service");
		if(keyword.trim() == "") {
			System.out.println("請輸入關鍵字");
			return null;
		}
		
		List<AnnouncementPo> anmPoList = null;
		try {
			announcementDao = new AnnouncementDaoImpl();
			anmPoList = announcementDao.selectKeyword(keyword);
			if(anmPoList.size() < 1) {
				System.out.println("沒有資料");
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
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
		// TODO Auto-generated method stub
		return null;
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


}
