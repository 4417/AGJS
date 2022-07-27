package agjs.dao;

import java.util.List;

import agjs.bean.SalesOrderStatusPo;

public interface SalesOrderStatusDao {

	List<SalesOrderStatusPo> select();
	SalesOrderStatusPo select(Integer id);
	SalesOrderStatusPo insert(SalesOrderStatusPo salesOrderStatus);
	SalesOrderStatusPo update(SalesOrderStatusPo salesOrderStatus);
	SalesOrderStatusPo delete(Integer id);
}
