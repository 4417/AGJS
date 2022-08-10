package agjs.service.impl.room;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agjs.bean.room.RoomUsedRecordPo;
import agjs.bean.room.RoomUsedRecordVo;
import agjs.dao.room.RoomUsedRecordDao;
import agjs.service.room.RoomUsedRecordService;

@Service
public class RoomUsedRecordServiceImpl implements RoomUsedRecordService<RoomUsedRecordPo> {

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
	public List<RoomUsedRecordVo> select(RoomUsedRecordVo recordModel) {
		List<RoomUsedRecordVo> select = new ArrayList<RoomUsedRecordVo>();
		System.out.println("-------------有進來service----------------");
		System.out.println(recordModel.getRoomName());
		System.out.println(recordModel.getOrderStartDate());
		if (recordModel.getRoomName() != null && recordModel.getOrderStartDate() != null) {
			select = roomUsedRecordDao.select(recordModel.getOrderStartDate(), recordModel.getRoomName());
			System.out.println("select" + select);
			System.out.println("----------有進入日期與房型----------");
		}
		if (recordModel.getOrderStartDate() != null) {
			select = roomUsedRecordDao.selectByDate(recordModel.getOrderStartDate());
			System.out.println(select);
			System.out.println("----------有進入service日期方法---------");
		}
		if (recordModel.getRoomName() != null) {
			select = roomUsedRecordDao.selectByRoomName(recordModel.getRoomName());
			System.out.println(select);
			System.out.println("--------有進入service房型方法-----------");
		}
		System.out.println("---------------service結束----------------");
		return select;

	}

	@Override
	public String select(List<RoomUsedRecordVo> recordModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
