package agjs.service.room;

import org.springframework.transaction.annotation.Transactional;

import agjs.bean.room.RoomVo_2;
import agjs.bean.user.UserPo;

public interface RoomService_2 {


	RoomVo_2 selectFromDR(RoomVo_2 vo, UserPo user);

}