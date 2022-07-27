package agjs.service;

import java.util.List;

import agjs.bean.SalesOrderStatusPo;

public interface SalesOrderStatusService {

	List<SalesOrderStatusPo> getOrderStatus();
	
	SalesOrderStatusPo getOrderStatus(Integer id);
}
