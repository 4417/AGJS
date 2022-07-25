package agjs.dao;

import java.util.List;

import agjs.bean.SalesOrderItemPo;

public interface SalesOrderItemDao {

	public abstract SalesOrderItemPo selectSalesOrderItem(Integer salesOrderItemId);

	public abstract List<SalesOrderItemPo> selectSalesOrderItem();

	public abstract SalesOrderItemPo insert(SalesOrderItemPo salesOrderItem);

	public abstract SalesOrderItemPo update(Integer salesOrderItemId);
	
	public abstract boolean delete(Integer salesOrderHeaderId, Integer roomStyleId, Integer orderRoomQuantity, Integer orderRoomPrice, Integer salesOrderItemId);
}
