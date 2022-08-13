package agjs.service.impl.order;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.journey.JourneyItemVo_2;
import agjs.bean.journey.JourneyPo;
import agjs.bean.order.SalesOrderFrontendAdminVo;
import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.order.SalesOrderItemPo;
import agjs.bean.order.SalesOrderItemVo;
import agjs.bean.order.SalesOrderVo;
import agjs.bean.room.RoomUsedRecordVo;
import agjs.bean.user.UserPo;
import agjs.dao.journey.JourneyItemDao_2;
import agjs.dao.order.SalesOrderHeaderDao;
import agjs.dao.order.SalesOrderStatusDao;
import agjs.dao.room.RoomDao_2;
import agjs.dao.room.RoomDao_3;
import agjs.dao.room.RoomStyleDao;
import agjs.dao.room.RoomUsedRecordDao;
import agjs.dao.user.UserDao_2;
import agjs.service.order.SalesOrderHeaderService;

@Service
public class SalesOrderHeaderServiceImpl implements SalesOrderHeaderService {
	@Autowired
	private SalesOrderHeaderDao dao;
	
	@Autowired
	private SalesOrderStatusDao statusDao;
	
	@Autowired
	private UserDao_2 userDao;
	
//	@Autowired
//	private SalesOrderItemDao salesOrderItemDao;
	
	@Autowired
	private JourneyItemDao_2 journeyItemDao;
	
	@Autowired
	private RoomDao_2 roomDao_2;
	
	@Autowired
	private RoomStyleDao roomStyleDao;
//	@Autowired
//	private JourneyTypeDao journeyTypeDao;


	@Transactional
	public SalesOrderHeaderPo create(SalesOrderHeaderPo SalesOrderHeader) {
//		//if login = true &&  (userPo.姓名 + userPo.身分證字號不重複)
//
//			//else create user account 
//		
//		//綁定訂單(how to 綁)
//		
//		//create SO header
//		
//		//create SO items
//		
//		//create Journey
//		
//		//串綠界API
//		
//		//新增客房使用紀錄
	
		return null;
	}
	
	//簡易查詢，僅查詢SQL中的SOHeader
	@Override
	public List<SalesOrderHeaderPo> getAll() {
		return dao.getAll();
	}
	

//查詢訂單(會顯示會員名稱與訂單狀態)
	@Override
	public List<SalesOrderVo> selectOrder() {
		
		List<SalesOrderVo> list = new ArrayList<SalesOrderVo>();
		List<SalesOrderHeaderPo> headerList = dao.getAll();
		
		//header
		for(SalesOrderHeaderPo i: headerList) {
			
			SalesOrderVo vo = new SalesOrderVo(); 
			vo.setSalesOrderHeaderId(i.getSalesOrderHeaderId());
			UserPo user = userDao.selectById(i.getUserId());
		    vo.setUserName(user.getUserName());
			vo.setOrderStartDate(i.getOrderStartDate());
			vo.setOrderEndDate(i.getOrderEndDate());
			vo.setCreateDate(i.getCreateDate());
			vo.setOrderChangeDate(i.getOrderChangeDate());
			String status = statusDao.selectNameById(i.getSalesOrderStatusId());
			vo.setSalesOrderStatus(status);
			vo.setOrderRemark(i.getOrderRemark());
			vo.setJourneyItemPrice(i.getJourneyPrice());
			vo.setOrderRoomPrice(i.getRoomPrice());
			list.add(vo);
		}
		return list;
	}
	
	//根據訂單id查詢底下的journeyItem
	@Override
	public List<JourneyItemVo_2> selectJourneyItems(Integer sohid){

		String sohidStr = sohid.toString(); 
		
		List<JourneyItemVo_2> resultList = new ArrayList<JourneyItemVo_2>();
		List<JourneyItemPo>	journeyItemList = journeyItemDao.selectBySohId(sohidStr);
		
		for(JourneyItemPo j: journeyItemList) {
			JourneyItemVo_2 vo = new JourneyItemVo_2();
			vo.setJourneyId(j.getJourneyItemId());
			JourneyPo temp = journeyItemDao.selectByJourneyId(j.getJourneyId());			
			vo.setJourneyName(temp.getJourneyName());
			int adultNum = j.getAdults();
			int childNum = j.getChildren();
			int adultPrice = temp.getJourneyPrice();
			int childPrice =temp.getJourneyPriceChild();
			vo.setAdults(adultNum);
			vo.setChildren(childNum);
			vo.setJourneyItemPrice((adultNum * adultPrice) + (childNum * childPrice));
			vo.setJourneyDate(j.getJourneyDate());
			resultList.add(vo);
		}
		
		System.out.println("Get Journey Items under SO(SOHServiceImpl):");
		System.out.println(resultList);
		
		return resultList;

	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
//各種查詢

	//訂單起始日期查詢，可供飯店後台管理使用
	@Override
	public List<SalesOrderHeaderPo> selecctByOrderStartDate(Date date){

		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("The date that I get(in service impl):" + formatDate.format(date));
		List<SalesOrderHeaderPo> list = dao.selectByStartDate(formatDate.format(date));
		return list;
	}
	
	
	//查詢使用者的所有訂單
	@Override
	public List<SalesOrderHeaderPo> selectByUserId(Integer userId){
		return dao.selectByUserId(userId);
	}

	//更新訂單
		//每天刷新未付款訂單，超過24hr就要改成已取消
		//前台 admin 更新
	@Override
	@Transactional
	public boolean updateSalesOrder(SalesOrderFrontendAdminVo frontendAdminVo) {
		
		Integer id = frontendAdminVo.getSalesOrderHeaderId();
		Date today = java.sql.Date.valueOf(LocalDate.now());
		
		Date strDate = frontendAdminVo.getStartDate();
		Date endDate = frontendAdminVo.getEndDate();

		System.out.println("front Admin Vo(serviceImpl) = " + frontendAdminVo);
		System.out.println("status = " + frontendAdminVo.getStatus());
		
		int statusId = statusDao.selectIdByName(frontendAdminVo.getStatus());
		SalesOrderHeaderPo po = dao.selectById(id);
		boolean changed = false;
		
		//mapping logic
		if(strDate!= null && endDate!=null) {
			
			//列出修改當日的房間使用清單(含房間id，)
			List<Object> roomList = new ArrayList<Object>();
			
			
			List<SalesOrderItemVo> soItemList = frontendAdminVo.getSalesOrderItemList();
			//取得該訂單內的所有房間明細
			
			
			
			for (SalesOrderItemVo sivo : soItemList) {
				//被其他張訂單佔走的房間清單(有先排除自己占用的房間)
				int roomUsedNum = roomDao_2.selectFromDateAndRoomStyle(strDate, endDate, sivo.getRoomName(), frontendAdminVo.getUserName());
				
				
				System.out.println("Sales Order Item list(SOH service impl):");
				System.out.println(sivo);
				//1. 房間庫存不足
				
			};
			
			
//			po.setOrderStartDate(frontendAdminVo.getStartDate());
//			po.setOrderEndDate(frontendAdminVo.getEndDate());
			changed = true;
			System.out.println("order date changed = " + changed);
		}
		if(statusId >0) {
			po.setSalesOrderStatusId(statusId);
			changed = true;
		}
		if(changed) {
			po.setOrderChangeDate(today);
			dao.update(po);
		}
		
		
		return false;
	}

	
}
