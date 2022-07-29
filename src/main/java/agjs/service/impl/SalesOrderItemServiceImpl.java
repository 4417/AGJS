package agjs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agjs.bean.order.SalesOrderItemPo;
import agjs.dao.SalesOrderItemDao;
import agjs.service.SalesOrderItemService;

@Service
public class SalesOrderItemServiceImpl implements SalesOrderItemService {

	@Autowired
	private SalesOrderItemDao dao;

//新增明細
	@Override
	public SalesOrderItemPo insert(SalesOrderItemPo bean) {
		SalesOrderItemPo result = null;
		if(bean!=null && bean.getSalesOrderItemId() !=null) {
			result = dao.insert(bean);
		}
		return result;
	}
	
//刪除明細
	@Override
	public boolean delete(Integer id) {
		boolean result = false;
		if(id!=null) {
			result = dao.delete(id);
		}
		return result;
	}
	
//依照訂單表頭id，取得所有訂單明細
	@Override
	public List<SalesOrderItemPo> getOrderItemByHeaderId(Integer headerId) {
		// TODO Auto-generated method stub
		return dao.select();
	}

//取得單一明細
	@Override
	public SalesOrderItemPo getOrderItem(Integer id) {
		
		return dao.select(id);
	}

//更新明細
	@Override
	public SalesOrderItemPo update(SalesOrderItemPo bean) {
		SalesOrderItemPo result = null;
		if(bean!=null && bean.getSalesOrderItemId() !=null) {
			result = dao.update(bean.getSalesOrderHeaderId(),bean.getRoomStyleId(), bean.getOrderRoomQuantity(),bean.getOrderRoomPrice(),bean.getSalesOrderItemId());
		}
		return result;
	}


}
