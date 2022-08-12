package agjs.dao.order;

import java.util.List;

import agjs.bean.order.SalesOrderStatusPo;


public interface SalesOrderStatusDao {

	List<SalesOrderStatusPo> select();
	SalesOrderStatusPo insert(SalesOrderStatusPo salesOrderStatus);
	SalesOrderStatusPo update(SalesOrderStatusPo salesOrderStatus);
	SalesOrderStatusPo delete(Integer id);
	Integer selectIdByName(String statusName);
	String selectNameById(Integer id);
}
