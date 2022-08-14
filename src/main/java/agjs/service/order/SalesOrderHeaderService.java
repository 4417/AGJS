package agjs.service.order;

import java.util.Date;
import java.util.List;

import agjs.bean.journey.JourneyItemVo;
import agjs.bean.journey.JourneyItemVo_2;
import agjs.bean.order.SalesOrderFrontendAdminVo;
import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.order.SalesOrderItemVo;
import agjs.bean.order.SalesOrderVo;



public interface SalesOrderHeaderService {
	
	SalesOrderHeaderPo create(SalesOrderHeaderPo SalesOrderHeader);

	List<SalesOrderHeaderPo> getAll();
	
	List<SalesOrderVo> selectOrder();
	
	List<SalesOrderHeaderPo> selectByUserId(Integer userId);
	
	List<SalesOrderHeaderPo> selecctByOrderStartDate(Date orderStartDate);
	
	boolean delete(Integer id);
	
	boolean updateSalesOrder(SalesOrderFrontendAdminVo salesOrderFrontendAdminVo);

	List<JourneyItemVo_2> selectJourneyItems(Integer sohid);
	
	List<SalesOrderItemVo> selectOrderItems(Integer sohid);
	


}
