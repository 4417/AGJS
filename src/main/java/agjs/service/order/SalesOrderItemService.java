package agjs.service.order;

import java.util.List;

import agjs.bean.order.SalesOrderItemPo;
import agjs.bean.order.SalesOrderItemVo;


public interface SalesOrderItemService {

	List<SalesOrderItemVo> getOrderItemByHeaderId(Integer id);

	SalesOrderItemPo getOrderItem(Integer id);
	
	boolean delete(Integer id);
	
	SalesOrderItemPo insert(SalesOrderItemPo bean);

	SalesOrderItemPo update(SalesOrderItemPo bean);
	
}
