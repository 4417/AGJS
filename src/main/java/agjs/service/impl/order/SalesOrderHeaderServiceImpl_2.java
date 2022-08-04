package agjs.service.impl.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.order.SalesOrderStatusPo;
import agjs.dao.order.SalesOrderHeaderDao;
import agjs.dao.order.SalesOrderStatusDao_2;
import agjs.service.order.SalesOrderHeaderService_2;

@Service
public class SalesOrderHeaderServiceImpl_2 implements SalesOrderHeaderService_2 {
	@Autowired
	private SalesOrderHeaderDao headerDao;
	@Autowired
	private SalesOrderStatusDao_2 statusDao;

	@Override
	public List<SalesOrderHeaderPo> selectByUserId(Integer id) {
		List<SalesOrderHeaderPo> list = headerDao.selectByUserId(id);
		for (SalesOrderHeaderPo headerPo : list) {
			SalesOrderStatusPo statusPo = statusDao.selectById(headerPo.getSalesOrderStatusId());
			String statusName = statusPo.getSalesOrderStatus();
			headerPo.setStatusName(statusName);
		}
		return list;
	}
}
