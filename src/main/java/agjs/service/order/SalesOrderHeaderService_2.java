package agjs.service.order;

import java.util.List;

import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.order.SalesOrderItemVo_2;

public interface SalesOrderHeaderService_2 {

	List<SalesOrderItemVo_2> selectByUserId(Integer id);
	
	SalesOrderItemVo_2 selectForOrderDateItem(Integer id,Integer header);
	
	List<SalesOrderItemVo_2> selectForRoom(Integer id,Integer header);
	
	List<SalesOrderItemVo_2> selectForJourney(Integer id,Integer header);

}