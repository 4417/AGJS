package agjs.service.impl.room;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agjs.bean.room.RoomUsedRecordVo;
import agjs.bean.room.RoomUsedRecordPo;
import agjs.dao.room.RoomUsedRecordDao;
import agjs.service.room.RoomUsedRecordService;

@Service
public class RoomUsedRecordServiceImpl implements RoomUsedRecordService<RoomUsedRecordPo> {

	@Autowired
	private RoomUsedRecordDao<RoomUsedRecordVo> roomUsedRecordDao;
	//選擇全部
	@Override
	@Transactional
	public List<RoomUsedRecordVo> getAll() {
		List<RoomUsedRecordVo> list = new ArrayList<RoomUsedRecordVo>();
		try {
			list = roomUsedRecordDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	@Transactional
	public List<RoomUsedRecordVo> select(){
		List<RoomUsedRecordVo> list= new ArrayList<RoomUsedRecordVo>();
		
		
		
		return list;
		
	}

}
