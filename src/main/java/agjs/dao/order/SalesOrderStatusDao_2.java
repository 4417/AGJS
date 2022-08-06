package agjs.dao.order;

import java.util.List;

import agjs.bean.order.SalesOrderStatusPo;

public interface SalesOrderStatusDao_2 {

	//select * from SalesOrderStatus where salesOrderStatusId = ?
	SalesOrderStatusPo selectById(Integer id);
	
	List<Object[]> selectByUserIdAndHeaderId(Integer id);

}