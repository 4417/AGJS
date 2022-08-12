package agjs.service.room;

import java.util.List;
import java.util.Map;

import agjs.bean.room.RoomUsedRecordPo;
import agjs.bean.room.RoomUsedRecordVo;

public interface RoomUsedRecordService {
	List<RoomUsedRecordVo> getAll();

	Map<String, Object> select(RoomUsedRecordVo recordVo);

	RoomUsedRecordPo update(RoomUsedRecordPo recordPo);

}
