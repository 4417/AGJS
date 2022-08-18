package agjs.service.impl.order;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.order.ECPayVo;
import agjs.bean.order.EcpayOrderPo;
import agjs.bean.order.OrderSubmitdVo;
import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.order.SalesOrderItemPo;
import agjs.bean.room.RoomUsedRecordPo;
import agjs.bean.user.UserPo;
import agjs.common.util.Tools;
import agjs.dao.journey.JourneyItemDao;
import agjs.dao.order.EcpayOrderDao;
import agjs.dao.order.SalesOrderHeaderDao;
import agjs.dao.order.SalesOrderHeaderDao_2;
import agjs.dao.order.SalesOrderItemDao_2;
import agjs.dao.room.RoomDao_2;
import agjs.dao.room.RoomUsedRecordDao;
import agjs.dao.user.UserDao_3;
import agjs.ecpay.payment.integration.service.AllInOneServiceImpl;
import agjs.service.order.OrderProcessService;
import agjs.service.order.SalesOrderItemService;
import agjs.service.user.RegisterMailService;
import agjs.service.user.UserService;

@Service
public class OrderProcessServiceImpl implements OrderProcessService {

	@Autowired
	private SalesOrderItemService salesOrderItemService;
	@Autowired
	private UserService userService;
	@Autowired
	private RegisterMailService registerMailService;

	private AllInOneServiceImpl allInOneService;;

	@Autowired
	private UserDao_3 userDao3;
	@Autowired
	private RoomDao_2 roomDao2;
	@Autowired
	private RoomUsedRecordDao recordDao;
	@Autowired
	private SalesOrderItemDao_2 salesOrderItemDao_2;
	@Autowired
	private SalesOrderHeaderDao_2 salesOrderHeaderDao_2;
	@Autowired
	private SalesOrderHeaderDao salesOrderHeaderDao;
	@Autowired
	private JourneyItemDao journeyItemDao;
	@Autowired
	private EcpayOrderDao ecpayOrderDao;

	@Override
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public UserPo checkOrderUser(UserPo user) throws Exception {

		if ("".equals(user.getUserName()) || "".equals(user.getUserIdentityNumber())
				|| "".equals(user.getUserBirthday()) || "".equals(user.getUserEmail())) {
			throw new Exception("會員資料缺少");
		} else {
			return userDao3.selectOrderUser2(user);
		}
	}

	// 訂單流程開始
	@Override
	@Transactional(rollbackFor = Exception.class)
	public SalesOrderHeaderPo orderProcess(OrderSubmitdVo orderSubmitdVo) throws Exception {

		UserPo user = checkOrderUser(orderSubmitdVo.getUser());

		if (user != null && checkSOH(orderSubmitdVo.getSoh())) {
			orderSubmitdVo.getSoh().setMsg("歡迎您再度光臨，即將前往付款頁面(綠界支付)");
			orderSubmitdVo.getSoh().setIsMember(1);
			SalesOrderHeaderPo po = createOrder(orderSubmitdVo, user);
			System.out.println(po);
			return po;

		} else if (user == null) {
			// 建立會員資料
			orderSubmitdVo.getSoh().setMsg("即將前往付款頁面(綠界支付) \n 已為您建立會員資料，請前往填寫的e-mail領取會員信件。");
			orderSubmitdVo.getSoh().setIsMember(0);
			UserPo userPo = genMember(orderSubmitdVo);
			System.out.println("userPo-" + userPo);
			if (userPo != null && userPo.getUserId() != null) {
				SalesOrderHeaderPo po = createOrder(orderSubmitdVo, userPo);
				System.out.println(po);
				System.out.println("發送mail");
				registerMailService.sendActivateMail(userPo, po);
				return po;
			} else {
				throw new Exception("user建立失敗");
			}
		}
		return null;
	};

	public UserPo genMember(OrderSubmitdVo orderSubmitdVo) throws Exception {

		System.out.println("create member:" + orderSubmitdVo.getUser());
		java.util.Date now = new java.util.Date();
		Tools tools = new Tools();
		UserPo userPo = new UserPo();
		userPo.setEmailVerifyStatus(false);
		userPo.setUserEmail(orderSubmitdVo.getUser().getUserEmail());
		userPo.setUserBirthday(orderSubmitdVo.getUser().getUserBirthday());
		userPo.setUserName(orderSubmitdVo.getUser().getUserName());
		userPo.setUserPhone(orderSubmitdVo.getUser().getUserPhone());
		userPo.setUserIdentityNumber(orderSubmitdVo.getUser().getUserIdentityNumber());
		userPo.setUserRegistrationDate(new java.sql.Date(now.getTime()));
		userPo.setUserAccount(tools.genAccount(10));
		userPo.setUserPassword(tools.genPassword(10));
		userPo.setVerifyMsg("auto");
		System.out.println(userPo);

		return userService.register(userPo);
	}

	// 新增流程
	@Override
	@Transactional
	public SalesOrderHeaderPo createOrder(OrderSubmitdVo orderSubmitdVo, UserPo user) throws Exception {

		// 新增訂單主檔案
		Integer sohId = createSOH(orderSubmitdVo.getSoh(), user.getUserId());
		System.out.println("建立訂單:" + sohId);
		// 新增綠界訂單編號
		EcpayOrderPo ecpayOrderPo = createEcpay(sohId);
		System.out.println("建立綠界訂單:" + ecpayOrderPo);
		// 新增訂單明細
		System.out.println("建立訂單明細:" + createSalesOrderItem(orderSubmitdVo.getSoiList(), sohId));
		// 新增行程明細
		System.out.println("建立行程明細:" + createjourneyItem(orderSubmitdVo.getJiList(), sohId));
		// 新增房間使用紀錄
		System.out.println(
				"建立房間使用紀錄:" + createRoomUsedRecord(user, orderSubmitdVo.getSoh(), orderSubmitdVo.getSoiList(), sohId));

		orderSubmitdVo.getSoh().setSalesOrderHeaderId(sohId);
		orderSubmitdVo.getSoh().setEcpayId(ecpayOrderPo.getEcpayId());
		orderSubmitdVo.getSoh().setSalesOrderStatusId(1);
		orderSubmitdVo.getSoh().setTradeDesc(orderSubmitdVo.getTradeDesc());
		registerMailService.sendOrderSuccessMail(user, orderSubmitdVo.getSoh());

		return orderSubmitdVo.getSoh();
	}

	// 建立訂單主檔
	@Override
	@Transactional
	public Integer createSOH(SalesOrderHeaderPo salesOrderHeaderPo, Integer userId) throws Exception {

		salesOrderHeaderPo.setUserId(userId);
		salesOrderHeaderPo.setSalesOrderStatusId(1);
		System.out.println("po=" + salesOrderHeaderPo);
		Integer id = (Integer) salesOrderHeaderDao_2.insert(salesOrderHeaderPo);
		if (id != null) {
			return id;
		} else {
			throw new Exception("主檔新增錯誤");
		}

	}

	// 產生綠界訂單ID
	public String genEcpayId(Integer len) throws Exception {

		Tools tools = new Tools();

		while (true) {
			String idString = "TE" + tools.genRandomNum(len);
			System.out.println(idString);
			if (ecpayOrderDao.select(idString) == null) {
				return idString;
			}
		}
	}

	@Override
	public EcpayOrderPo createEcpay(Integer sohId) throws Exception {

		EcpayOrderPo ecpayOrderPo = new EcpayOrderPo();
		ecpayOrderPo.setSalesOrderHeaderId(sohId);
		ecpayOrderPo.setEcpayId(genEcpayId(8));
		String pk = (String) ecpayOrderDao.insert(ecpayOrderPo);
		return ecpayOrderPo;
	}

	// 檢查主檔資料
	@Override
	public Boolean checkSOH(SalesOrderHeaderPo salesOrderHeaderPo) {

		if (salesOrderHeaderPo != null && salesOrderHeaderPo.getCreateDate() != null
				&& salesOrderHeaderPo.getOrderEndDate() != null && salesOrderHeaderPo.getOrderStartDate() != null
				&& salesOrderHeaderPo.getRoomPrice() != null) {
			return true;
		}
		return false;
	}

	// 建立訂單明細
	@Override
	@Transactional
	public List<Integer> createSalesOrderItem(List<SalesOrderItemPo> salesOrderItemPoList, Integer sohId)
			throws Exception {

		if (salesOrderItemPoList != null && sohId != null) {
			List<SalesOrderItemPo> poList = checkSalesOrderItem(salesOrderItemPoList, sohId);

			if (poList != null) {
				List<Integer> soiIdList = new ArrayList<Integer>();
				for (int i = 0; i < poList.size(); i++) {
					System.out.println("po=" + poList.get(i));
					Serializable sli = salesOrderItemDao_2.insert(poList.get(i));
					System.out.println("建立 房型明細:" + sli);
					if (sli != null) {
						soiIdList.add(Integer.parseInt(sli.toString()));
					} else {
						throw new Exception();
					}
				}
				return soiIdList;
			}
			throw new Exception("訂單明細錯誤");
		} else {
			throw new Exception("訂單明細錯誤");
		}
	}

	@Override
	public List<SalesOrderItemPo> checkSalesOrderItem(List<SalesOrderItemPo> salesOrderItemPoList, Integer sohId)
			throws Exception {

		for (int i = 0; i < salesOrderItemPoList.size(); i++) {
			SalesOrderItemPo po = salesOrderItemPoList.get(i);
			if (po.getOrderRoomPrice() != null && po.getOrderRoomQuantity() != null && po.getRoomStyleId() != null) {
				salesOrderItemPoList.get(i).setSalesOrderHeaderId(sohId);
			} else {
				throw new Exception("訂單明細資料錯誤");
			}
		}
		return salesOrderItemPoList;
	}

	@Override
	@Transactional
	public List<Integer> createjourneyItem(List<JourneyItemPo> journeyItemPoList, Integer sohId) throws Exception {

		List<Integer> soiIdList = new ArrayList<Integer>();

		if (journeyItemPoList == null || journeyItemPoList.size() == 0) {
			System.out.println("沒有加購行程");
			return null;
		} else {
			List<JourneyItemPo> poList = checkjourneyItem(journeyItemPoList, sohId);
			for (int i = 0; i < poList.size(); i++) {
				Serializable sli = journeyItemDao.insert(poList.get(i));
				System.out.println("insert 行程訂單:" + sli);
				if (sli != null) {
					soiIdList.add(Integer.parseInt(sli.toString()));
				} else {
					throw new Exception("行程訂單錯誤");
				}
			}
		}
		return soiIdList;

	}

	@Override
	public List<JourneyItemPo> checkjourneyItem(List<JourneyItemPo> journeyItemPoList, Integer sohId) throws Exception {

		for (int i = 0; i < journeyItemPoList.size(); i++) {
			JourneyItemPo po = journeyItemPoList.get(i);
			if (po.getJourneyId() != null && po.getJourneyDate() != null && po.getAdults() != null) {
				journeyItemPoList.get(i).setSohId(sohId);
			} else {
				throw new Exception("行程明細資料錯誤");
			}
		}
		return journeyItemPoList;
	}

	@Override
	public String callAllInOneService(SalesOrderHeaderPo po) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String orderDate = sdf.format(new Date());
		allInOneService = new AllInOneServiceImpl();
		Integer amount = 0;
		ECPayVo ecPayVo = new ECPayVo();

		if (po.getJourneyPrice() != null) {
			amount = po.getRoomPrice()+ po.getJourneyPrice();
		} else {
			amount = po.getRoomPrice() ;
		}
		if (po.getEcpayId() != null) {
			System.out.println("id:" + po.getEcpayId() + " ecpayId:" + po.getSalesOrderHeaderId());
			ecPayVo.setMerchantTradeNo(po.getEcpayId());
			ecPayVo.setItemName(po.getTradeDesc());
			ecPayVo.setMerchantTradeDate(orderDate);
			ecPayVo.setTotalAmount(amount.toString());
			ecPayVo.setTradeDesc(po.getTradeDesc());
			ecPayVo.setClientBackURL("http://localhost:8081/AGJS/main/user_login.html");
			ecPayVo.setReturnURL("http://35.189.165.131:8081/AGJS/main/ecpayprocess/paydone");
			System.out.println(ecPayVo);
			return allInOneService.payment(ecPayVo);
		} else {
			return null;
		}
	}

	// 新增房間使用紀錄
	@Override
	public List<RoomUsedRecordPo> createRoomUsedRecord(UserPo user, SalesOrderHeaderPo salesOrderHeaderPo,
			List<SalesOrderItemPo> salesOrderItemPoList, Integer sohId) throws Exception {

		List<RoomUsedRecordPo> poList = checkRoomUsedRecord(user, salesOrderHeaderPo, salesOrderItemPoList, sohId);
		for (RoomUsedRecordPo po : poList) {
			if (po != null) {
				System.out.println("insert:" + po);
				recordDao.update(po);
			} else {
				throw new Exception();
			}
		}
		return poList;
	}

	@Override
	public List<RoomUsedRecordPo> checkRoomUsedRecord(UserPo user, SalesOrderHeaderPo salesOrderHeaderPo,
			List<SalesOrderItemPo> salesOrderItemPoList, Integer sohId) throws Exception {

		if (salesOrderItemPoList != null && sohId != null) {
			List<RoomUsedRecordPo> roomUsedRecordPoList = new ArrayList<RoomUsedRecordPo>();
			for (SalesOrderItemPo po : salesOrderItemPoList) {
				if (po.getOrderRoomQuantity() != null && po.getRoomStyleId() != null) {

					List<?> roomPoList = roomDao2.selectForRoomStyleId(salesOrderHeaderPo.getOrderStartDate(),
							salesOrderHeaderPo.getOrderEndDate(), po.getRoomStyleId(), po.getOrderRoomQuantity());
					System.out.println("room==" + roomPoList);

					if (roomPoList.size() == po.getOrderRoomQuantity() && roomPoList != null) {
						for (int i = 0; i < po.getOrderRoomQuantity(); i++) {
							RoomUsedRecordPo roomUsedRecordPo = new RoomUsedRecordPo();
							roomUsedRecordPo.setStartDate(salesOrderHeaderPo.getOrderStartDate());
							roomUsedRecordPo.setEndDate(salesOrderHeaderPo.getOrderEndDate());
							roomUsedRecordPo.setOderHeaderId(sohId);
							roomUsedRecordPo.setUserName(user.getUserName());
							roomUsedRecordPo.setRoomId((Integer) roomPoList.get(i));
							roomUsedRecordPoList.add(roomUsedRecordPo);
						}
					} else {
						throw new Exception("房間記錄比數 數量錯誤");
					}
				} else {
					throw new Exception("房間紀錄資料錯誤");
				}
			}
			return roomUsedRecordPoList;
		} else {
			throw new Exception("訂單明細，主檔Id錯誤");
		}
	}

}
