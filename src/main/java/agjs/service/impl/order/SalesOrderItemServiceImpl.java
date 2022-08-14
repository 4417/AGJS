package agjs.service.impl.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.midi.VoiceStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonRawValue;

import agjs.bean.order.SalesOrderItemPo;
import agjs.bean.order.SalesOrderItemVo;
import agjs.dao.order.SalesOrderItemDao;
import agjs.dao.room.RoomDao_2;
import agjs.service.order.SalesOrderItemService;
import net.bytebuddy.asm.Advice.Return;

@Service
public class SalesOrderItemServiceImpl implements SalesOrderItemService {

	@Autowired
	private SalesOrderItemDao dao;
	
	@Autowired
	private RoomDao_2 roomDao2;

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
	public List<SalesOrderItemVo> getOrderItemByHeaderId(Integer sohid) {
		
		if(sohid != null) {
			
			System.out.println("Sales Order item service impl: " + sohid);
			List<Object[]> itemList = dao.selectAllOrderItems(sohid);
			List<SalesOrderItemVo> resultList = new ArrayList<SalesOrderItemVo>();
			
			for (Object[] i : itemList) {
				SalesOrderItemVo vo = new SalesOrderItemVo();
				vo.setSalesOrderItemId((Integer)i[0]);
				vo.setSalesOrderHeaderId((Integer)i[1]);
				vo.setRoomStyleId((Integer)i[2]);
				vo.setOrderRoomPrice((Integer)i[5]);
				vo.setRoomName((String)i[3]);
				vo.setOrderRoomQuantity((Integer)i[4]);
				resultList.add(vo);
				System.out.println("VO:");
				System.out.println(vo);
			}
			
			return resultList;
		}
		
		return null;
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
