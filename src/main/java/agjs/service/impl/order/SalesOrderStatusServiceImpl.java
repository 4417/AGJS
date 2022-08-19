package agjs.service.impl.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agjs.bean.order.SalesOrderStatusPo;
import agjs.dao.order.SalesOrderStatusDao;
import agjs.service.order.SalesOrderStatusService;

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
