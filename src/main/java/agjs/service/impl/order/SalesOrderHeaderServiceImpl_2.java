package agjs.service.impl.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.order.SalesOrderItemVo_2;
import agjs.bean.order.SalesOrderStatusPo;
import agjs.dao.order.SalesOrderHeaderDao;
import agjs.dao.order.SalesOrderStatusDao_2;
import agjs.service.order.SalesOrderHeaderService_2;

@Service
public class SalesOrderHeaderServiceImpl_2 implements SalesOrderHeaderService_2 {
	@Autowired
	private SalesOrderHeaderDao headerDao;
	@Autowired
	private SalesOrderStatusDao_2 statusDao;

	@Override
	public List<SalesOrderItemVo_2> selectByUserId(Integer id) {
		
		List<SalesOrderItemVo_2> listVo= new ArrayList<SalesOrderItemVo_2>();
		//根據登入的會員ID，找尋對應的訂單Header集合，header對status為多對1
		List<SalesOrderHeaderPo> listPo = headerDao.selectByUserId(id);
		//訂單Header集合跑迴圈
		for (SalesOrderHeaderPo headerPo : listPo) {
			//選出對應的訂單狀態
			SalesOrderStatusPo statusPo = statusDao.selectById(headerPo.getSalesOrderStatusId());
			SalesOrderItemVo_2 vo= new SalesOrderItemVo_2();
			////將Status表的訂單狀態與Header表的資料丟進VO集合裡
			vo.setSalesOrderStatus(statusPo.getSalesOrderStatus());
			vo.setSalesOrderHeaderId(headerPo.getSalesOrderHeaderId());
			vo.setCreateDate(headerPo.getCreateDate());
			vo.setRoomPrice(headerPo.getRoomPrice());
			vo.setJourneyPrice(headerPo.getJourneyPrice());
			listVo.add(vo);
		}
		return listVo;
	}
	
	@Override
	public List<SalesOrderItemVo_2> selectForOrderItem(Integer id) {
		
		List<SalesOrderItemVo_2> listVo= new ArrayList<SalesOrderItemVo_2>();
		List<Object[]> listItem = statusDao.selectByUserIdAndHeaderId(id);
		//訂單Header集合跑迴圈
		for (Object[] index : listItem) {
			
		}
		return listVo;
	}
}
