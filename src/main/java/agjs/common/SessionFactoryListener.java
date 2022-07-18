package agjs.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import agjs.common.util.HibernateUtil;

@WebListener
public class SessionFactoryListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		HibernateUtil.getSessionFactory();
		System.out.println("SessionFactoryListener context Initialized");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		HibernateUtil.closeSessionFactory();
		System.out.println("SessionFactoryListener context Destroyed");
	}
}
