package agjs.service.order;

import java.util.List;

import agjs.bean.order.SalesOrderStatusPo;


public interface SalesOrderStatusService {

	List<SalesOrderStatusPo> getAllStatus();
	
	SalesOrderStatusPo getOrderStatus(Integer id);
}
