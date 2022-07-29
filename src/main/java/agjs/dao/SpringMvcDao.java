package agjs.dao;

import java.util.List;

import agjs.bean.RoomUsedRecordPo;

public interface SpringMvcDao {

	public List<RoomUsedRecordPo> selectRoomUsedRecord();

}