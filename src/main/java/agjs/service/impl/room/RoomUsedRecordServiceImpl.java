package agjs.service.impl.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Map<String, Object> select(RoomUsedRecordVo recordModel) {
		Map<String, Object> respMap = new HashMap<>();
		List<RoomUsedRecordVo> select;
		System.out.println("-------------有進來service----------------");
		System.out.println(recordModel.getRoomName());
		System.out.println(recordModel.getOrderStartDate());
		if (recordModel.getRoomName() != null && recordModel.getOrderStartDate() != null) {
			select = roomUsedRecordDao.select(recordModel.getOrderStartDate(), recordModel.getRoomName());
			System.out.println("select" + select);
			System.out.println("----------有進入日期與房型----------");
		} else if (recordModel.getOrderStartDate() != null && recordModel.getRoomName() == null) {
			select = roomUsedRecordDao.selectByDate(recordModel.getOrderStartDate());
			System.out.println(select);
			System.out.println("----------有進入service日期方法---------");
		} else if (recordModel.getRoomName() != null && recordModel.getOrderStartDate() == null) {
			select = roomUsedRecordDao.selectByRoomName(recordModel.getRoomName());
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
	public String select(List<RoomUsedRecordVo> recordModel) {
		// TODO Auto-generated method stub
		return null;
	}

}