package ru.unionfreearts.webservice.dbService;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

/**
 * Created by Михалыч on 06.05.2017.
 */
public class HibernateSessionFactory {

    private static final SessionFactory sessionFactory = configureSessionFactory();

    private static SessionFactory configureSessionFactory()
            throws HibernateException {
        File hibernateConfig = new File("src/main/resources/config/hibernate.cfg.xml");
        Configuration configuration = new Configuration().configure(hibernateConfig);
        return configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
