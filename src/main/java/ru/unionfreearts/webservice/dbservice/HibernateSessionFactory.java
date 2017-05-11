package ru.unionfreearts.webservice.dbservice;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Михалыч on 06.05.2017.
 */
public class HibernateSessionFactory {
	static final Logger logger = LoggerFactory.getLogger(HibernateSessionFactory.class);

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static SessionFactory getSessionFactory(){
        logger.debug("get sessionFactory");
		return sessionFactory;
    }

    public static void shutdown(){
		logger.debug("sessionFactory shutdown");
        sessionFactory.close();
    }
}
