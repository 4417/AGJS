package agjs.dao.room;

import java.util.Date;
import java.util.List;

import agjs.bean.room.RoomUsedRecordPo;
import agjs.bean.room.RoomUsedRecordVo;

public interface RoomUsedRecordDao<T> {
	List<T> getAll();

	List<RoomUsedRecordVo> select(Date date, String string);

	List<RoomUsedRecordVo> selectByDate(Date orderStartDate);

	List<RoomUsedRecordVo> selectByRoomName(String roomName);

	RoomUsedRecordPo update(RoomUsedRecordPo recordPo);
}
