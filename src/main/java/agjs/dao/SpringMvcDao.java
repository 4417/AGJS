package agjs.dao;

import java.util.List;

import agjs.bean.room.RoomUsedRecordPo;

public interface SpringMvcDao {

	public List<RoomUsedRecordPo> selectRoomUsedRecord();

}