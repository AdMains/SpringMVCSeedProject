package com.zhangzhihao.SpringMVCSeedProject.Utils;


import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("Hibernate.cfg.xml");
			//在下面添加映射，不要写在配置文件中
			configuration.addAnnotatedClass(User.class);
			StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			return configuration.buildSessionFactory(ssrb.build());
		}
		catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed !" + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}
}
