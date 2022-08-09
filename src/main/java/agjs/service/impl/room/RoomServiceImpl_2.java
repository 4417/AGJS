package agjs.service.impl.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.room.RoomVo_2;
import agjs.dao.room.RoomDao_2;
import agjs.service.room.RoomService_2;

@Service
public class RoomServiceImpl_2 implements RoomService_2 {
	@Autowired
	private RoomDao_2 dao;
	
	@Override
	@Transactional
	public String selectFromDR(RoomVo_2 vo) {
		Integer room=dao.selectFromDateAndRoomStyle(vo.getOrderStartDate(), 
				vo.getOrderEndDate(), vo.getRoomName());
		if(room>=vo.getOrderRoomQuantity()) {
			
		}else {
			return "房間數量不足，請重新選擇時間";
		}
		return null;
	}

}
