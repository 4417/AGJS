package agjs.service.room;

import java.util.List;

import agjs.bean.room.RoomCardVo;
import agjs.bean.room.RoomStatusVo;

public interface RoomStatusService {
	
	RoomStatusVo searchEmptyRoomByDateRange(RoomStatusVo roomStatusVo);
	
	List<RoomCardVo> searchRoomCardByEmptyRoomTypeId(String[] styleIdStrings);

}
