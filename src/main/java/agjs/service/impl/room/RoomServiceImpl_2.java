package agjs.service.impl.room;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.journey.JourneyPo;
import agjs.bean.order.SalesOrderItemVo_2;
import agjs.bean.room.RoomVo_2;
import agjs.bean.user.UserPo;
import agjs.dao.order.SalesOrderStatusDao_2;
import agjs.dao.room.RoomDao_2;
import agjs.service.room.RoomService_2;

@Service
public class RoomServiceImpl_2 implements RoomService_2 {
	@Autowired
	private RoomDao_2 dao;
	@Autowired
	private SalesOrderStatusDao_2 statusDao;
	
	@Override
	@Transactional
	public RoomVo_2 selectFromDR(RoomVo_2 vo,UserPo user) {
		//先查詢符合數量的房型空房有幾個
		List<Object[]> roomResult =	statusDao.selectForRoomItem(user.getUserId(),vo.getSalesOrderHeaderId());
		List<SalesOrderItemVo_2> listVo= new ArrayList<SalesOrderItemVo_2>();
		for(Object[] index: roomResult) {
			//(String) index[0]：其中一筆房間明細的ROOM_NAME
			//(Integer) index[1]：其中一筆房間明細的ORDER_ROOM_QUANTITY
			Integer room=dao.selectFromDateAndRoomStyle(vo.getOrderStartDate(), 
					vo.getOrderEndDate(), (String) index[0]);
			System.out.println("房間數量="+room);
			//若剩餘房間數量小於客戶原訂單明細數量，則拒絕客戶修改
			if(room<(Integer) index[1]) {
				vo.setMsg("房間數量不足，請重新選擇時間");
				
				break;
			}
			vo.setMsg("成功修改");
		}
		
		return vo;
//		if(room>=vo.getOrderRoomQuantity()) {
//			
//		}else {
//			return "房間數量不足，請重新選擇時間";
//		}
	}

}
