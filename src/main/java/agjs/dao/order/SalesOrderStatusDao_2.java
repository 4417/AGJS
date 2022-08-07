package agjs.dao.order;

import java.util.List;

import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.order.SalesOrderStatusPo;

public interface SalesOrderStatusDao_2 {

	//select * from SalesOrderStatus where salesOrderStatusId = ?
	SalesOrderStatusPo selectById(Integer id);
	
	SalesOrderHeaderPo selectByUserIdAndHeaderId(Integer id,Integer header);
	
	List<Object[]> selectForRoomItem(Integer id,Integer header);
	
	List<Object[]> selectForJourneyItem(Integer id,Integer header);

}