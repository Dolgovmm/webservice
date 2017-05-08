package ru.unionfreearts.webservice.dbservice;

import org.hibernate.*;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Михалыч on 30.04.2017.
 */
public class DbServiceImpl<T> implements DbService<T> {

    private final SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private Class<T> tClass;

    public DbServiceImpl(Class<T> tClass) {
        this.tClass = tClass;
    }

    public long add(T entity) throws HibernateException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);//todo: add return id of saved entity
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

    public long remove(T entity) throws HibernateException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(entity);
            transaction.commit();
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
        List<T> list;
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

    public long update(T entity) {
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
