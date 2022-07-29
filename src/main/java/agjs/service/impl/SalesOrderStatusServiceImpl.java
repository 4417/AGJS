package agjs.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.order.SalesOrderStatusPo;
import agjs.common.config.SpringConfig;
import agjs.dao.SalesOrderStatusDao;
import agjs.service.SalesOrderStatusService;

@Service
public class SalesOrderStatusServiceImpl implements SalesOrderStatusService {
	@Autowired
	private SalesOrderStatusDao dao;
	
//	public static void main(String[] args) {
//		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//		SalesOrderStatusService salesOrderStatusService = context.getBean("SalesOrderStatusService", SalesOrderStatusService.class);
//		
//		SessionFactory sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
//		Session session = sessionFactory.getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//		
//		List<SalesOrderStatusPo> selects = salesOrderStatusService.getOrderStatus();
//		System.out.println("selects="+selects);
//		
//		transaction.commit();
//		session.close();
//		
//		((ConfigurableApplicationContext) context).close();
//	}
	

	@Override
	public List<SalesOrderStatusPo> getAllStatus() {
//		List<SalesOrderStatusPo> statusList = new ArrayList<SalesOrderStatusPo>();
		
		return dao.select();
	}

//get order status by Order id? input = SalesOrderHeaderPo?
	@Override
	public SalesOrderStatusPo getOrderStatus(Integer id) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
