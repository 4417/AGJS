package agjs.service.order;

import java.util.List;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.order.EcpayOrderPo;
import agjs.bean.order.OrderSubmitdVo;
import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.order.SalesOrderItemPo;
import agjs.bean.room.RoomUsedRecordPo;
import agjs.bean.user.UserPo;

public interface OrderProcessService {

	SalesOrderHeaderPo orderProcess(OrderSubmitdVo orderSubmitdVo) throws Exception;

	Integer ecpayComplete(Integer sohId) throws Exception;

	UserPo checkOrderUser(UserPo user) throws Exception;

	EcpayOrderPo createEcpay(Integer sohId) throws Exception;

	SalesOrderHeaderPo createOrder(OrderSubmitdVo orderSubmitdVo, UserPo user) throws Exception;

	Boolean checkSOH(SalesOrderHeaderPo salesOrderHeaderPo);

	Integer createSOH(SalesOrderHeaderPo salesOrderHeaderPo, Integer userId) throws Exception;

	List<SalesOrderItemPo> checkSalesOrderItem(List<SalesOrderItemPo> salesOrderItemPoList, Integer sohId)
			throws Exception;

	List<Integer> createSalesOrderItem(List<SalesOrderItemPo> salesOrderItemPoList, Integer sohId) throws Exception;

	List<JourneyItemPo> checkjourneyItem(List<JourneyItemPo> journeyItemPoList, Integer sohId) throws Exception;

	List<Integer> createjourneyItem(List<JourneyItemPo> journeyItemPoList, Integer sohId) throws Exception;

	String callAllInOneService(SalesOrderHeaderPo po);

	List<RoomUsedRecordPo> createRoomUsedRecord(UserPo user, SalesOrderHeaderPo salesOrderHeaderPo,
			List<SalesOrderItemPo> salesOrderItemPoList, Integer sohId) throws Exception;

	List<RoomUsedRecordPo> checkRoomUsedRecord(UserPo user, SalesOrderHeaderPo salesOrderHeaderPo,
			List<SalesOrderItemPo> salesOrderItemPoList, Integer sohId) throws Exception;

//	void sendActivateMail(UserPo user) throws Exception;
}
