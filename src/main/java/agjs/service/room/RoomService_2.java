package agjs.service.room;

import agjs.bean.room.RoomVo_2;
import agjs.bean.user.UserPo;

public interface RoomService_2 {


	RoomVo_2 selectFromDR(RoomVo_2 vo, UserPo user);

	void updateDate(RoomVo_2 vo, UserPo user) throws Exception;

	String cancelOrder(Integer id) throws Exception;

}