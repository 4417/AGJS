package agjs.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import agjs.bean.SalesOrderStatusPo;
import agjs.dao.SalesOrderStatusDao;
import agjs.service.SalesOrderStatusService;

@Service
public class SalesOrderStatusServiceImpl implements SalesOrderStatusService {
	@Autowired
	private SalesOrderStatusDao dao;
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringConfig.java");
		SalesOrderStatusService salesOrderStatusService = context.getBean("SalesOrderStatusService", SalesOrderStatusService.class);
		
		SessionFactory sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		List<SalesOrderStatusPo> selects = salesOrderStatusService.getOrderStatus();
		System.out.println("selects="+selects);
		
		transaction.commit();
		session.close();
		
		((ConfigurableApplicationContext) context).close();
	}
	

	@Override
	public List<SalesOrderStatusPo> getOrderStatus() {
//		List<SalesOrderStatusPo> statusList = new ArrayList<SalesOrderStatusPo>();
		
		return dao.select();
	}

	@Override
	public SalesOrderStatusPo getOrderStatus(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
