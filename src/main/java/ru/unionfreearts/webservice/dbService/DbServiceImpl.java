package ru.unionfreearts.webservice.dbService;

import org.hibernate.*;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Михалыч on 30.04.2017.
 */
//@Service
public class DbServiceImpl<T> implements DbService<T> {

    private final SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private Class<T> tClass;

    public DbServiceImpl(Class<T> tClass) {
//        Type mySuperclass = getClass().getGenericSuperclass();
//        Type tType = ((ParameterizedType)mySuperclass).getActualTypeArguments()[0];
//        String className = tType.toString().split(" ")[1];
//        Class tClass = null;
//        try {
//            tClass = Class.forName(className);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        this.tClass = tClass;
    }

    public long add(T entity) throws HibernateException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            long id = (Long) session.save(entity);//todo: add return id of saved entity
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException ex) {
            throw new HibernateException(ex);
        } finally {
            transaction.rollback();
            if (session != null || session.isOpen()) {
                session.close();
            }
        }
    }

    public T get(long id) throws HibernateException {
        Session session = sessionFactory.openSession();
        try {
            T entity = (T) session.get(tClass, id);
            session.close();
            return entity;
        } catch (HibernateException ex) {
            throw new HibernateException(ex);
        } finally {
            if (session != null || session.isOpen()) {
                session.close();
            }
        }
    }

    public int remove(long id) throws HibernateException {
        Session session = sessionFactory.openSession();
        try {
            T entity = session.get(tClass, id);
            session.remove(entity);
            session.close();
            return 1;
        } catch (HibernateException ex) {
            throw new HibernateException(ex);
        } finally {
            if (session != null || session.isOpen()) {
                session.close();
            }
        }
    }

    public List<T> getAll() {
        Session session = sessionFactory.openSession();
        List<T> list = new ArrayList<T>();
        try {
            CriteriaQuery<T> query = session.getCriteriaBuilder().createQuery(tClass);
            query.select(query.from(tClass));
            Query q = session.createQuery(query);
            list = q.getResultList();
            session.close();
            return list;
        } catch (HibernateException ex) {
            throw new HibernateException(ex);
        } finally {
            if (session != null || session.isOpen()) {
                session.close();
            }
        }
    }

    public int update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
            transaction.commit();
            session.close();
            return 1;
        } catch (HibernateException ex) {
            throw new HibernateException(ex);
        } finally {
            transaction.rollback();
            if (session != null || session.isOpen()) {
                session.close();
            }
        }
    }
}
