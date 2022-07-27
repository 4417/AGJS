package agjs.service;

import java.util.List;



import agjs.bean.SalesOrderHeaderPo;


public interface SalesOrderHeaderService {
	SalesOrderHeaderPo create(SalesOrderHeaderPo SalesOrderHeader);

	List<SalesOrderHeaderPo> getAll();
}
