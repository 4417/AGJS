package agjs.service.room;

import java.util.List;
import java.util.Map;

import agjs.bean.room.RoomUsedRecordVo;

public interface RoomUsedRecordService {
	List<RoomUsedRecordVo> getAll();

	Map<String, Object> select(RoomUsedRecordVo recordModel);

	String select(List<RoomUsedRecordVo> recordModel);

}
