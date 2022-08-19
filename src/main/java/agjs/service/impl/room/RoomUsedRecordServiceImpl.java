package agjs.service.impl.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agjs.bean.room.RoomUsedRecordPo;
import agjs.bean.room.RoomUsedRecordVo;
import agjs.dao.room.RoomUsedRecordDao;
import agjs.service.room.RoomUsedRecordService;

@Service
public class RoomUsedRecordServiceImpl implements RoomUsedRecordService {

	@Autowired
	private RoomUsedRecordDao<RoomUsedRecordVo> roomUsedRecordDao;
	
	// 選擇全部
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

	// 利用日期與房型尋找
	@Override
	@Transactional
	public Map<String, Object> select(RoomUsedRecordVo recordVo) {
		Map<String, Object> respMap = new HashMap<>();
		List<RoomUsedRecordVo> select;
		System.out.println("-------------有進來service----------------");
		System.out.println(recordVo.getRoomName());
		System.out.println(recordVo.getOrderStartDate());
		if (recordVo.getRoomName() != null && recordVo.getOrderStartDate() != null) {
			select = roomUsedRecordDao.select(recordVo.getOrderStartDate(), recordVo.getRoomName());
			System.out.println("select" + select);
			System.out.println("----------有進入日期與房型----------");
		} else if (recordVo.getOrderStartDate() != null && recordVo.getRoomName() == null) {
			select = roomUsedRecordDao.selectByDate(recordVo.getOrderStartDate());
			System.out.println(select);
			System.out.println("----------有進入service日期方法---------");
		} else if (recordVo.getRoomName() != null && recordVo.getOrderStartDate() == null) {
			select = roomUsedRecordDao.selectByRoomName(recordVo.getRoomName());
			System.out.println(select);
			System.out.println("--------有進入service房型方法-----------");
		} else {
			select = new ArrayList<RoomUsedRecordVo>();
			respMap.put("msg", "請重新篩選，無此資料");
		}
		System.out.println("---------------service結束----------------");
		respMap.put("data", select);
		return respMap;

	}

	@Override
	public RoomUsedRecordPo update(RoomUsedRecordPo recordPo) {
		
		roomUsedRecordDao.update(recordPo);
		
		return recordPo;
	}

}