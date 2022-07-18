package agjs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import agjs.bean.RoomUsedRecordPo;
import agjs.model.SpringMvcDao;
import agjs.service.SpringMvcService;


@Service
public class SpringMvcServiceImpl implements SpringMvcService {

	@Autowired
	@Qualifier("SpringMvcDao1")
	private SpringMvcDao springMvcDao;

	public SpringMvcServiceImpl() {
		
		System.out.println("SpringMvcService init");
	}

	@Override
	public List<RoomUsedRecordPo> getRoomUsedRecordAll() {
		
		return springMvcDao.selectRoomUsedRecord();

	}

}
