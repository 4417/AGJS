package agjs.service.room;

import org.springframework.transaction.annotation.Transactional;

import agjs.bean.room.RoomVo_2;

public interface RoomService_2 {

	String selectFromDR(RoomVo_2 vo);

}