package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import po.*;

public class HibernateUtil {

	static SessionFactory factory=null;
	
	public static SessionFactory getSessionFactory() {
		Configuration config=new Configuration().configure();
		config.addAnnotatedClass(AccountPO.class);
		config.addAnnotatedClass(AccountTransferPO.class);
		config.addAnnotatedClass(ConsumerAddressPO.class);
		config.addAnnotatedClass(ConsumerPO.class);
		config.addAnnotatedClass(OrderMultipleDishPO.class);
		config.addAnnotatedClass(DishOrderPO.class);
		config.addAnnotatedClass(OrderSingleDishPO.class);
		config.addAnnotatedClass(ProviderDiscountPO.class);
		config.addAnnotatedClass(ProviderMultipleDishPO.class);
		config.addAnnotatedClass(ProviderPO.class);
		config.addAnnotatedClass(ProviderSingleDishPO.class);
		config.addAnnotatedClass(ProviderForUpdatePO.class);
		ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		SessionFactory sessionFactory=config.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}
	
	public static Session getSession() {
		if (factory==null) {
			factory=getSessionFactory();
		}
		return factory.getCurrentSession();
	}
}
