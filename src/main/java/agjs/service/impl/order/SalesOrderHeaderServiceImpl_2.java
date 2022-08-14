package agjs.service.impl.order;

import java.util.ArrayList;
import java.util.Date;
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
	public SalesOrderItemVo_2 selectForOrderDateItem(Integer id,Integer header) {
		
		SalesOrderItemVo_2 vo= new SalesOrderItemVo_2();
		SalesOrderHeaderPo po = statusDao.selectByUserIdAndHeaderId(id,header);
		vo.setCreateDate(po.getCreateDate());
		vo.setOrderStartDate(po.getOrderStartDate());
		vo.setOrderEndDate(po.getOrderEndDate());
		return vo;
	}
	
	@Override
	public List<SalesOrderItemVo_2> selectForRoom(Integer id,Integer header) {
		
		List<Object[]> roomResult =	statusDao.selectForRoomItem(id,header);
		List<SalesOrderItemVo_2> listVo= new ArrayList<SalesOrderItemVo_2>();
//		System.out.println("roomResult="+roomResult);
		for(Object[] index: roomResult) {
			SalesOrderItemVo_2 vo= new SalesOrderItemVo_2();
			vo.setRoomName((String) index[0]);
			vo.setOrderRoomQuantity((Integer) index[1]);
			vo.setOrderRoomPrice((Integer) index[2]);
			Integer roomPrice=((Integer) index[1])*((Integer) index[2]);
			vo.setRoomPrice(roomPrice);
			listVo.add(vo);
			
		}
		return listVo;
	}
	
	@Override
	public List<SalesOrderItemVo_2> selectForJourney(Integer id,Integer header) {
		
		List<Object[]> journeyResult =	statusDao.selectForJourneyItem(id,header);
		List<SalesOrderItemVo_2> listVo= new ArrayList<SalesOrderItemVo_2>();
		System.out.println("journeyResult="+journeyResult);
		for(Object[] index: journeyResult) {
			SalesOrderItemVo_2 vo= new SalesOrderItemVo_2();
			vo.setJourneyName((String) index[0]);
			vo.setAdults((Integer) index[1]);
			vo.setChildren((Integer) index[2]);
			Integer journeyPrice= ((Integer) index[1])*((Integer) index[3])+((Integer) index[2])*((Integer) index[4]);
			vo.setJourneyPrice(journeyPrice);
			vo.setJourneyDate((Date) index[5]);
			listVo.add(vo);
		}
		return listVo;
	}
}
