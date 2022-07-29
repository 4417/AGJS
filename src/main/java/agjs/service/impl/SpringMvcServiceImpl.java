package agjs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import agjs.bean.room.RoomUsedRecordPo;
import agjs.dao.SpringMvcDao;
import agjs.service.SpringMvcService;

@Service
public class SpringMvcServiceImpl implements SpringMvcService {

	@Autowired
	@Qualifier("SpringMvcDao1")
	private SpringMvcDao springMvcDao;

	@Override
	public List<RoomUsedRecordPo> getRoomUsedRecordAll() {
		return springMvcDao.selectRoomUsedRecord();
	}

}
