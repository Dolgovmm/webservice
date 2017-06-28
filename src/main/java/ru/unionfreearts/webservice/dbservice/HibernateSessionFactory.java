package ru.unionfreearts.webservice.dbservice;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class implement static methods to get instance of Hibernate session factory.
 * @author M.Dolgov
 * @date 6.05.2017
 */
public class HibernateSessionFactory {
	static final Logger logger = LoggerFactory.getLogger(HibernateSessionFactory.class);

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static SessionFactory getSessionFactory(){
		return sessionFactory;
    }

    public static void shutdown(){
        sessionFactory.close();
    }
}
