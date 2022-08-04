package agjs.dao.order;

import agjs.bean.order.SalesOrderStatusPo;

public interface SalesOrderStatusDao_2 {

	//select * from SalesOrderStatus where salesOrderStatusId = ?
	SalesOrderStatusPo selectById(Integer id);

}