package ru.unionfreearts.webservice.dbservice;

import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.unionfreearts.webservice.dbservice.specification.Specification;
import ru.unionfreearts.webservice.entity.AbstractEntity;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Михалыч on 30.04.2017.
 */
public class DbServiceImpl<T extends AbstractEntity> implements DbService<T> {
	static final Logger logger = LoggerFactory.getLogger(DbServiceImpl.class);

    private Class<T> tClass;

    public DbServiceImpl(Class<T> tClass) {
        logger.debug("constructor DbServiceImpl begin with " + tClass + " class");
		this.tClass = tClass;
		logger.debug("constructor DbServiceImpl end with " + tClass + " class");
    }

    public long add(T entity) throws HibernateException {
		logger.debug("add method dbServiceImpl with entity: " + entity.toString() + " " + tClass);
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
		logger.debug("hibernate session received from HibernateSessionFactory");
        Transaction transaction = session.beginTransaction();
		logger.debug("begin transaction");
        try {
            session.save(entity);//todo: add return id of saved entity
			logger.debug("save entity " + entity.toString() + tClass);
            transaction.commit();
			logger.debug("transaction commit");
            return 1;
        } catch (HibernateException ex) {
            logger.error("HibernateException on save entity " + entity.toString() + tClass
                    + " with message: " + ex.getMessage());
			transaction.rollback();
			logger.error("transaction rollback because HibernateException");
            throw new HibernateException(ex);
        } finally {
            logger.debug("if session for add entity is open then close it");
			if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public T get(long id) throws HibernateException {
		logger.debug("get method dbServiceImpl with id: " + id + tClass);
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
		logger.debug("hibernate session received form HibernateSessionFactory");
        try {
            T entity = session.get(tClass, id);
			logger.debug("get entity " + entity.toString() + tClass);
            return entity;
        } catch (HibernateException ex) {
            logger.error("HibernateException on get entity by id: " + id + tClass
                    + " with message: " + ex.getMessage());
			throw new HibernateException(ex);
        } finally {
            logger.debug("if session for get entity is open then close it");
			if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public long remove(T entity) throws HibernateException {
		logger.debug("remove method dbServiceImpl with entity: " + entity.toString() + tClass);
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
		logger.debug("hibernate session received form HibernateSessionFactory");
        Transaction transaction = session.beginTransaction();
		logger.debug("begin transaction");
        try {
            session.remove(entity);
			logger.debug("remove entity " + entity.toString() + tClass);
            transaction.commit();
			logger.debug("transaction commit");
            return 1;
        } catch (HibernateException ex) {
            logger.error("HibernateException on remove entity " + entity.toString() + tClass + " with message: "
                    + ex.getMessage());
			transaction.rollback();
			logger.error("transaction rollback because HibernateException");
            throw new HibernateException(ex);
        } finally {
            logger.debug("if session for remove entity is open then close it");
			if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public long update(T entity) throws HibernateException{
		logger.debug("update method dbServiceImpl with class: " + tClass);
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
		logger.debug("hibernate session received form HibernateSessionFactory");
        Transaction transaction = session.beginTransaction();
		logger.debug("begin transaction");
        try {
            session.update(entity);
            logger.debug("update entity " + entity.toString() + tClass);
			transaction.commit();
			logger.debug("transaction commit");
            return 1;
        } catch (HibernateException ex) {
            logger.error("HibernateException on update entity " + entity.toString() + tClass + " with messsage: "
                    + ex.getMessage());
			transaction.rollback();
			logger.error("transaction rollback because HibernateException");
            throw new HibernateException(ex);
        } finally {
            logger.debug("if session for update entity is open then close it");
			if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<T> query(Specification<T> specification) throws HibernateException{
        logger.debug("query method dbServiceImpl with class: " + tClass);
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        logger.debug("hibernate session received form HibernateSessionFactory");
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        logger.debug("create criteriaBuilder from session");
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(specification.getType());
        logger.debug("create criteria from criteriaBuilder");
        Root<T> root = criteriaQuery.from(specification.getType());
        logger.debug("create root from criteria");
        Predicate predicate = specification.toPredicate(root, criteriaBuilder);
        logger.debug("create predicate from criteria");
        criteriaQuery.where(predicate);
        logger.debug("set predicate and get result list");
        return session.createQuery(criteriaQuery).getResultList();
    }
}
