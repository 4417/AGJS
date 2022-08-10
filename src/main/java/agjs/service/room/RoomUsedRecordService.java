package agjs.service.room;

import java.util.Date;
import java.util.List;

import agjs.bean.room.RoomUsedRecordVo;

public interface RoomUsedRecordService<T> {
	List<RoomUsedRecordVo> getAll();

	List<RoomUsedRecordVo> select(RoomUsedRecordVo recordModel);

	String select(List<RoomUsedRecordVo> recordModel);

}
