package agjs.service.impl.room;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agjs.bean.room.RoomUsedRecordPo;
import agjs.dao.room.RoomUsedRecordDao;
import agjs.service.room.RoomUsedRecordService;

@Service
public class RoomUsedRecordServiceImpl implements RoomUsedRecordService<RoomUsedRecordPo> {

	@Autowired
	private RoomUsedRecordDao<RoomUsedRecordPo> roomUsedRecordDao;

	@Override
	@Transactional
	public List<RoomUsedRecordPo> getAll() {
		List<RoomUsedRecordPo> list = new ArrayList<RoomUsedRecordPo>();
		try {
			list = roomUsedRecordDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

}
