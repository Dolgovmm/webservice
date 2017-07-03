package ru.unionfreearts.webservice.dbservice;

import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import ru.unionfreearts.webservice.dbservice.specification.Specification;
import ru.unionfreearts.webservice.entity.AbstractEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Class describes work with table DB over hibernate framework.
 * @author M.Dolgov
 * @date 30.04.2017
 */
@Service
public class DbServiceImpl<T extends AbstractEntity> implements DbService<T> {
	static final Logger logger = LoggerFactory.getLogger(DbServiceImpl.class);

    private Class<T> tClass;

    public DbServiceImpl(Class<T> tClass) {
		this.tClass = tClass;
    }

    public DbServiceImpl() {
    }

    public T add(T entity) throws HibernateException {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (HibernateException ex) {
			transaction.rollback();
            throw new HibernateException(ex);
        } finally {
			closeSession(session);
        }
    }

    public T get(long id) throws HibernateException {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            T entity = session.get(tClass, id);
            return entity;
        } catch (HibernateException ex) {
			throw new HibernateException(ex);
        } finally {
        	closeSession(session);
        }
    }

    public boolean remove(T entity) throws HibernateException {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(entity);
            transaction.commit();
            return true;
        } catch (HibernateException ex) {
			transaction.rollback();
            throw new HibernateException(ex);
        } finally {
        	closeSession(session);
        }
    }

    public boolean update(T entity) throws HibernateException{
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
			transaction.commit();
            return true;
        } catch (HibernateException ex) {
			transaction.rollback();
            throw new HibernateException(ex);
        } finally {
        	closeSession(session);
        }
    }

    public List<T> query(Specification<T> specification) throws HibernateException{
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(specification.getType());
        Root<T> root = criteriaQuery.from(specification.getType());
        Predicate predicate = specification.toPredicate(root, criteriaBuilder);
        criteriaQuery.where(predicate);
        return session.createQuery(criteriaQuery).getResultList();
    }
    
    private void closeSession(Session session) {
    	if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
