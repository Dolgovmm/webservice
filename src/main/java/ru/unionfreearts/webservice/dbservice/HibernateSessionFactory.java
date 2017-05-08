package ru.unionfreearts.webservice.dbservice;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Михалыч on 06.05.2017.
 */
public class HibernateSessionFactory {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void shutdown(){
        sessionFactory.close();
    }
}
