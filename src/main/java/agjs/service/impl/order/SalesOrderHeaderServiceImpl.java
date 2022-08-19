package agjs.service.impl.order;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.journey.JourneyItemVo_2;
import agjs.bean.journey.JourneyPo;
import agjs.bean.order.SalesOrderFrontendAdminVo;
import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.order.SalesOrderItemVo;
import agjs.bean.order.SalesOrderVo;
import agjs.bean.room.RoomUsedRecordPo;
import agjs.bean.user.UserPo;
import agjs.dao.impl.room.RoomUsedRecordDao_3;
import agjs.dao.journey.JourneyItemDao_2;
import agjs.dao.order.SalesOrderHeaderDao;
import agjs.dao.order.SalesOrderItemDao;
import agjs.dao.order.SalesOrderStatusDao;
import agjs.dao.room.RoomDao_2;
import agjs.dao.room.RoomStyleDao;
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
	
	@Autowired
	private SalesOrderItemDao soItemDao;
	
	@Autowired
	private SalesOrderStatusDao statusDao2;
	
	@Autowired
	private JourneyItemDao_2 journeyItemDao;
	
	@Autowired
	private RoomDao_2 roomDao_2;
	
	@Autowired
	private RoomUsedRecordDao_3 roomURDao_3;
	
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
		//每天刷新未付款訂單，超過24hr就要改成已取消(寫在SQL)
		//前台 admin 更新
	@Override
	@Transactional(rollbackFor = Exception.class)
	public SalesOrderFrontendAdminVo updateSalesOrder(SalesOrderFrontendAdminVo frontendAdminVo) throws Exception {
		//前端傳入訂單資訊
		Integer id = frontendAdminVo.getSalesOrderHeaderId();
		Date today = java.sql.Date.valueOf(LocalDate.now());
		//取得欲修改的開始結束日期 及 欲修改的訂單狀態
		
		Date strDate = frontendAdminVo.getSalesOrderStartDate();
		Date endDate = frontendAdminVo.getSalesOrderEndDate();
//		int statusId = statusDao.selectIdByName(frontendAdminVo.getSalesOrderStatus());
		
		boolean aval = false; //可行性預設為不可行
		
		//根據前端資訊拉出資料庫相同id的訂單主檔
		SalesOrderHeaderPo po = dao.selectById(id);
		
		//取得該訂單內的所有房間明細
		List<Object[]> soItemList = soItemDao.selectAllOrderItems(id);
		
		if(strDate!= null && endDate!=null) {
			//過濾掉想要修改成相同日期的情況
			if(strDate != po.getOrderStartDate() && endDate != po.getOrderEndDate()) {

				//Object sequence in soItemList = SALES_ORDER_ITEM_ID, SALES_ORDER_HEADER_ID, ROOM_STYLE_ID, ROOM_NAME, ORDER_ROOM_QUANTITY, ORDER_ROOM_PRICE
				//根據明細每筆房型
				for (Object[] sivo : soItemList) {
					
					System.out.println("==========================");
					System.out.println("SOH service impl: 查詢每筆明細");

					//可訂空房數量(有先加入被同一張訂單占用的房間)
					Integer emptyRoomNum = roomDao_2.selectFromDateAndRoomStyle(strDate, endDate, id, (String)sivo[3]);
					System.out.println("剩餘房間數量: " + emptyRoomNum);
					
					System.out.println("需求房型名稱:" + sivo[3]);
					System.out.println("需求房型數量:" + sivo[4]);
					System.out.println("==========================");
					//若房間庫存不足
					if(emptyRoomNum < (Integer)sivo[4]) {
						frontendAdminVo.setErrMsg("此日期可訂購房間數量不足，修改訂單失敗");		
						aval = false;
					}
				};
				if(frontendAdminVo.getErrMsg() != null) {
					aval = false;
					frontendAdminVo.setMsg("此日期可訂購房間數量不足，修改訂單失敗");
					return frontendAdminVo;
				}else {
					//journey的接續查詢
					
					aval = true;
				}
				
				//如果查詢日期後修改可行
				if(aval == true) { 	
					//直接修改訂單主檔的日期
					po.setOrderStartDate(strDate);
					po.setOrderEndDate(endDate);
					
					//更新訂單修改日期
					po.setOrderChangeDate(today);
					
					//刪除房間使用紀錄
					roomDao_2.deleteByHeaderId(id);
					
					//Object sequence in soItemList = SALES_ORDER_ITEM_ID, SALES_ORDER_HEADER_ID, ROOM_STYLE_ID, ROOM_NAME, ORDER_ROOM_QUANTITY, ORDER_ROOM_PRICE
					//新增房間使用紀錄
					for (Object[] vo : soItemList) {
						RoomUsedRecordPo roomUsedPo = new RoomUsedRecordPo();

						Integer rstyleId = (Integer) vo[2];
						String roomName = roomURDao_3.getNamebyStyleId(rstyleId);
						Integer roomQuantity = (Integer)vo[4];
						
						//指定房號 room id
						List<Integer> emptyRoomIdList =  roomURDao_3.selectEmptyRoomList(strDate, endDate, id, roomName);
					    
						System.out.println("==========================");
						System.out.println("系統查詢到的空房: ");
						for(int j = 0; j < roomQuantity; j++) {
							System.out.println(emptyRoomIdList.get(j));

							roomUsedPo.setRoomId(emptyRoomIdList.get(j));
							
							//訂單表頭相關資訊
							roomUsedPo.setOderHeaderId(id);
							roomUsedPo.setStartDate(strDate);
							roomUsedPo.setEndDate(endDate);
							
							System.out.println("設定客房用記錄的訂單id: " + id);
							System.out.println("設定客房用記錄的入住日期: " + strDate);
							System.out.println("設定客房用記錄的退房日期: " + endDate);
							
							UserPo user = userDao.selectById(po.getUserId());
							roomUsedPo.setUserName(user.getUserName());
							System.out.println("設定客房用記錄的會員姓名: " + user.getUserName());

							//新增客房使用紀錄
							roomURDao_3.insert(roomUsedPo);
						}
						System.out.println("==========================");
					}
					
					//實際更新headerDao
					dao.update(po);
					frontendAdminVo.setMsg("修改成功!");
					return frontendAdminVo;
					
				}
			}
		}
		return frontendAdminVo;

	}

	@Override
	public List<SalesOrderItemVo> selectOrderItems(Integer sohid) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
