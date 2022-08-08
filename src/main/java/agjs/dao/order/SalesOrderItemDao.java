package agjs.dao.order;

import java.util.List;

import agjs.bean.order.SalesOrderItemPo;


public interface SalesOrderItemDao {

	public abstract SalesOrderItemPo select(Integer id);

	public abstract List<SalesOrderItemPo> select();

	public abstract SalesOrderItemPo insert(SalesOrderItemPo salesOrderItem);

	public abstract SalesOrderItemPo update(Integer salesOrderHeaderId, Integer roomStyleId, Integer orderRoomQuantity, Integer orderRoomPrice, Integer salesOrderItemId);
	
//	public abstract boolean delete(Integer salesOrderHeaderId, Integer roomStyleId, Integer orderRoomQuantity, Integer orderRoomPrice, Integer salesOrderItemId);
	public abstract boolean delete(Integer salesOrderHeaderId);
}
