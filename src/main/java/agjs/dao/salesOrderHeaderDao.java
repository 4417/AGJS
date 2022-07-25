package agjs.dao;

import java.util.Date;
import java.util.List;

import agjs.bean.SalesOrderHeaderPo;

public interface salesOrderHeaderDao {
	
	SalesOrderHeaderPo select(SalesOrderHeaderPo salesOrderHeaderId);

	List<SalesOrderHeaderPo> select();

	SalesOrderHeaderPo insert(SalesOrderHeaderPo bean);

	SalesOrderHeaderPo update(Integer userId, Date createDate, Date orderStartDate, Date orderEndDate, Date orderChangeDate, Integer salesOrderStatusId, String orderRemark, Integer roomPrice, Integer journeyPrice, Integer salesOrderHeaderId);

	boolean delete(SalesOrderHeaderPo salesOrderHeaderId);
}
