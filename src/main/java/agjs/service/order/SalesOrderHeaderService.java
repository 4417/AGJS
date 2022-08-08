package agjs.service.order;

import java.util.Date;
import java.util.List;

import agjs.bean.order.SalesOrderFrontendAdminVo;
import agjs.bean.order.SalesOrderHeaderPo;



public interface SalesOrderHeaderService {
	
	SalesOrderHeaderPo create(SalesOrderHeaderPo SalesOrderHeader);

	List<SalesOrderHeaderPo> getAll();
	
	List<SalesOrderHeaderPo> selectById(Integer id);
	
	List<SalesOrderHeaderPo> selectByUserId(Integer userId);
	
	List<SalesOrderHeaderPo> selecctByOrderStartDate(Date orderStartDate);
	
	boolean delete(Integer id);
	
//	boolean updateSalesOrder(SalesOrderFrontendAdminVo salesOrderFrontendAdminVo);
	boolean updateSalesOrder(SalesOrderHeaderPo po);
}
