package ru.unionfreearts.webservice.dbservice;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.File;

/**
 * Created by Михалыч on 06.05.2017.
 */
public class HibernateSessionFactory {
    private static SessionFactory sessionFactory = buildSessionFactory();

    protected static SessionFactory buildSessionFactory() {
        // A SessionFactory is set up once for an application!
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure() // configures settings from hibernate.cfg.xml
//                .build();
//        try {
//            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
//        }
//        catch (Exception e) {
//            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
//            // so destroy it manually.
//            StandardServiceRegistryBuilder.destroy( registry );
//
//            throw new ExceptionInInitializerError("Initial SessionFactory failed" + e);
//        }

        return new Configuration().configure().buildSessionFactory();
    }

//    private static final SessionFactory sessionFactory = configureSessionFactory();
//
//    private static SessionFactory configureSessionFactory()
//            throws HibernateException {
//        File hibernateConfig = new File("/src/main/resources/hibernate.cfg.xml");
//        Configuration configuration = new Configuration().configure(hibernateConfig);
//        return configuration.buildSessionFactory();
//    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void shutdown(){
        sessionFactory.close();
    }
}
