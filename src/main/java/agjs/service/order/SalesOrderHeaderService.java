package agjs.service.order;

import java.util.List;

import agjs.bean.order.SalesOrderHeaderPo;



public interface SalesOrderHeaderService {
	
	SalesOrderHeaderPo create(SalesOrderHeaderPo SalesOrderHeader);

	List<SalesOrderHeaderPo> getAll();
	
	boolean delete(Integer id);
}
