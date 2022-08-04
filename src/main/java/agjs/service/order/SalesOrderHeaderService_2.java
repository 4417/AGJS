package agjs.service.order;

import java.util.List;

import agjs.bean.order.SalesOrderHeaderPo;

public interface SalesOrderHeaderService_2 {

	List<SalesOrderHeaderPo> selectByUserId(Integer id);

}