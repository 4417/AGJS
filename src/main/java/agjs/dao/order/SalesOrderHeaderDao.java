package agjs.dao.order;

import java.util.Date;
import java.util.List;

import agjs.bean.order.SalesOrderHeaderPo;


public interface SalesOrderHeaderDao {
	
	List<SalesOrderHeaderPo> getAll();

	SalesOrderHeaderPo selectById(Integer salesOrderHeaderId);
	
	List<SalesOrderHeaderPo> selectByStartDate(String orderStartDate);
	
	List<SalesOrderHeaderPo> selectByStatus(Integer salesOrderStatusId);
	
	List<SalesOrderHeaderPo> selectByUserId(Integer userId);
	
	// 再加入複合查詢
	
	SalesOrderHeaderPo insert(SalesOrderHeaderPo bean);

	boolean update(SalesOrderHeaderPo bean);

	boolean delete(Integer salesOrderHeaderId);
}
