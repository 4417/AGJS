package agjs.service.impl.room;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.room.RoomUsedRecordPo;
import agjs.bean.room.RoomVo_2;
import agjs.bean.user.UserPo;
import agjs.dao.order.SalesOrderHeaderDao;
import agjs.dao.order.SalesOrderStatusDao_2;
import agjs.dao.room.RoomDao_2;
import agjs.service.room.RoomService_2;

@Service
public class RoomServiceImpl_2 implements RoomService_2 {
	@Autowired
	private RoomDao_2 dao;
	@Autowired
	private SalesOrderStatusDao_2 statusDao;
	@Autowired
	private SalesOrderHeaderDao headerDao;

	@Override
	@Transactional
	public RoomVo_2 selectFromDR(RoomVo_2 vo, UserPo user) {
		System.out.println("會員ID=" + user.getUserId());
		System.out.println("訂單主檔號碼=" + vo.getSalesOrderHeaderId());

		// 先查詢符合數量的房型空房有幾個
		List<Object[]> roomResult = statusDao.selectForRoomItem(user.getUserId(), vo.getSalesOrderHeaderId());
		List<Object[]> journeyResult = statusDao.selectForJourneyItem(user.getUserId(), vo.getSalesOrderHeaderId());
		for (Object[] index : roomResult) {
			// (String) index[0]：其中一筆房間明細的ROOM_NAME
			// (Integer) index[1]：其中一筆房間明細的ORDER_ROOM_QUANTITY
			System.out.println("起始日=" + vo.getOrderStartDate());
			System.out.println("結束日=" + vo.getOrderEndDate());
			System.out.println("會員姓名=" + user.getUserName());
			System.out.println("需求房型=" + (String) index[0]);
			System.out.println("需求數量=" + (Integer) index[1]);
			Integer room = dao.selectFromDateAndRoomStyle(vo.getOrderStartDate(), vo.getOrderEndDate(),
					vo.getSalesOrderHeaderId(), (String) index[0]);
			System.out.println("房間數量=" + room);
			// 若剩餘房間數量小於客戶原訂單明細數量，則拒絕客戶修改
			if (room < (Integer) index[1]) {
				vo.setErrMsg("房間數量不足，請重新選擇時間");
			}
		}
		if (vo.getErrMsg() != null) {
			return vo;
		} else {
			// 再查詢當天行程是否符合數量
			for (Object[] index : journeyResult) {
				// (String) index[0]：其中一筆行程明細的JOURNEY_NAME
				// (Integer) index[1]：其中一筆行程明細的大人人數
				// (Integer) index[2]：其中一筆行程明細的小孩人數
				Integer limit = dao.selectByJourneyName((String) index[0]);
				System.out.println("人數上限為=" + limit);
				System.out.println("行程=" + (String) index[0]);
				System.out.println("起始日=" + vo.getOrderStartDate());
				Integer total = (Integer) index[1] + (Integer) index[2];
				System.out.println("欲參與人數=" + total);
				Integer person = dao.selectByDateAndName(vo.getOrderStartDate(), vo.getSalesOrderHeaderId(),
						(String) index[0]);
				System.out.println("目前參與人數=" + person);
				Integer remain = limit - person;
				System.out.println("剩餘名額=" + remain);

				if (remain < total) {
					vo.setMsg("行程數量不足，若確認修改時間，行程費用將不予退回");
				}
				vo.setMsg("成功修改");
			}
		}
		return vo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateDate(RoomVo_2 vo, UserPo user) throws Exception {
		SalesOrderHeaderPo pastBean = headerDao.selectById(vo.getSalesOrderHeaderId());
		List<RoomUsedRecordPo> po = new ArrayList<RoomUsedRecordPo>();
		// 修改訂單主檔日期
		pastBean.setSalesOrderHeaderId(vo.getSalesOrderHeaderId());
		pastBean.setOrderChangeDate(vo.getOrderChangeDate());
		pastBean.setOrderStartDate(vo.getOrderStartDate());
		pastBean.setOrderEndDate(vo.getOrderEndDate());

		boolean update = headerDao.update(pastBean);
		boolean delete = false;
		boolean insert = false;
		boolean updateJourney = false;
		// 若修改成功，則先刪除舊房間使用紀錄
		System.out.println("更新訂單日期=" + update);
		if (update == true) {
			delete = dao.deleteByHeaderId(vo.getSalesOrderHeaderId());
			System.out.println("刪除舊紀錄=" + delete);
			// 刪除成功，則新增新房間使用紀錄
			if (delete == true) {
				List<Object[]> roomResult = statusDao.selectForRoomItem(user.getUserId(), vo.getSalesOrderHeaderId());
				for (Object[] index : roomResult) {
					List<?> roomList = dao.selectForRoomId(vo.getOrderStartDate(), vo.getOrderEndDate(),
							vo.getSalesOrderHeaderId(), (String) index[0]);
					System.out.println("roomList=" + roomList);

					for (int i = 0; i < (Integer) index[1]; i++) {
						RoomUsedRecordPo roomPo = new RoomUsedRecordPo();
						roomPo.setOderHeaderId(vo.getSalesOrderHeaderId());
						roomPo.setStartDate(vo.getOrderStartDate());
						roomPo.setEndDate(vo.getOrderEndDate());
						roomPo.setUserName(user.getUserName());
						roomPo.setRoomId((Integer) roomList.get(i));
						System.out.println("房號=" + roomList.get(i));
						po.add(roomPo);
					}
				}
				insert = dao.insertByHeaderId(po);
				System.out.println("新增紀錄=" + insert);
				// 房間使用紀錄全部新增成功後，修改行程明細日期
				if (insert == true) {
					// 行程數量不足時不修改也不取消
					if (vo.getMsg() == "行程數量不足，若確認修改時間，行程費用將不予退回") {
						vo.setMsg("修改成功！(行程數量不足)");
					} else {
						List<JourneyItemPo> journeyPo = dao.selectbySohId(vo.getSalesOrderHeaderId());
						for (JourneyItemPo index : journeyPo) {

							index.setJourneyDate(vo.getOrderStartDate());
							updateJourney = dao.updateJourneyDate(index);
						}
					}
					System.out.println("修改行程日期=" + updateJourney);
					if (updateJourney == true) {
						vo.setMsg("修改成功！(最終)");
					}else {
						throw new Exception();
					}
				}else {
					throw new Exception();
				}
				
			}else {
				throw new Exception();
			}

		} else {
			throw new Exception();
		}
	}

	@Override
	@Transactional
	public String cancelOrder(Integer id) throws Exception {
		SalesOrderHeaderPo pastPo = headerDao.selectById(id);
		pastPo.setSalesOrderStatusId(4);
		boolean status = headerDao.update(pastPo);
		boolean delete = false;
		if (status == true) {
			delete = dao.deleteByHeaderId(id);
		}
		if (delete == true) {
			return "取消成功！";
		}
		return "取消失敗！";
	}

}
