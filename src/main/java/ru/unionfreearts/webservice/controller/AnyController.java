package ru.unionfreearts.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.unionfreearts.webservice.dbservice.specification.Specification;
import ru.unionfreearts.webservice.entity.AbstractEntity;
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;
import java.util.List;

/**
 * AnyController implement CRUD methods to work with any entity.
 * @author M.Dolgov
 * @date 11.05.2017
 */
public class AnyController<T extends AbstractEntity> {
    static final Logger logger = LoggerFactory.getLogger(AnyController.class);

	public T add(Repository repository, String json, Class tClass){
        T entity = null;
        
        try {
			entity = (T) new ObjectMapper().readValue(json, tClass);
			repository.add(entity);
			if (logger.isDebugEnabled()) {
	        	StringBuilder sb = new StringBuilder();
	        	sb.append("add entity instance of ");
	        	sb.append(tClass);
	        	sb.append("from json string {");
	        	sb.append(json);
	        	sb.append("}: ");
	        	sb.append(entity.toString());
	        	logger.debug(sb.toString());
	        }
			return entity;
		} catch (IOException ex) {
			logJsonException(json, ex);
			return null;
		} catch (HibernateException ex) {
        	if (logger.isErrorEnabled()) {
            	StringBuilder sb = new StringBuilder();
            	sb.append("HibernateException on add entity ");
            	sb.append(entity.toString());
            	sb.append(" instance of ");
            	sb.append(tClass);
            	sb.append(" to repository with message: ");
            	sb.append(ex.getMessage());
            	logger.error(sb.toString());
            }   
        	return null;
		}
    }

    public T getById(Repository repository, long id, Class tClass) {
        T entity = null;
        try {
        	entity = (T)repository.get(id);

			if (entity != null) {
				if (logger.isDebugEnabled()) {
					StringBuilder sb = new StringBuilder();
					sb.append("get entity instance of");
					sb.append(tClass);
					sb.append(" by id {");
					sb.append(id);
					sb.append("}: ");
					sb.append(entity.toString());
					logger.debug(sb.toString());
				}
			}
	        return entity;
        } catch (HibernateException ex) {
        	if (logger.isErrorEnabled()) {
            	StringBuilder sb = new StringBuilder();
            	sb.append("HibernateException on get ");
            	sb.append(tClass);
            	sb.append(" from repository by id {");
            	sb.append(id);
            	sb.append("} with message: ");
            	sb.append(ex.getMessage());
            	logger.error(sb.toString());
            }
        	return null;
        }
    }

    public List<T> getAll(Repository repository, Specification<T> specification, Class tClass) {
        try {
        	List<T> list = repository.query(specification);
        	if (list != null) {
				if (logger.isDebugEnabled()) {
					StringBuilder sb = new StringBuilder();
					sb.append("get all entity instance of ");
					sb.append(tClass);
					sb.append(": ");
					sb.append(list.toString());
					logger.debug(sb.toString());
				}
			}
        	return list;
        } catch (HibernateException ex) {
        	if (logger.isErrorEnabled()) {
            	StringBuilder sb = new StringBuilder();
            	sb.append("HibernateException on get all instances of");
            	sb.append(tClass);
            	sb.append(" from repository with message: ");
            	sb.append(ex.getMessage());
            	logger.error(sb.toString());
            }
        	return null;
        }
    }

    public boolean remove(Repository repository, String json, Class tClass) {
        T entity = null;
        boolean removed;
        
        try{
	        entity = (T) new ObjectMapper().readValue(json, tClass);
	        
	        removed = repository.remove(entity);
	        
	        if (logger.isDebugEnabled()) {
	        	StringBuilder sb = new StringBuilder();
	        	sb.append("remove entity instance of ");
	        	sb.append(tClass);
	        	sb.append("from json string {");
	        	sb.append(json);
	        	sb.append("}: ");
	        	sb.append(entity.toString());
	        	logger.debug(sb.toString());
	        }
	        return removed;
	        
        } catch (IOException ex) {
        	logJsonException(json, ex);
        	return false;
        	
        } catch (HibernateException ex) {
        	if (logger.isErrorEnabled()) {
            	StringBuilder sb = new StringBuilder();
            	sb.append("HibernateException on remove entity ");
            	sb.append(entity.toString());
            	sb.append(" instance of ");
            	sb.append(tClass);
            	sb.append(" to repository with message: ");
            	sb.append(ex.getMessage());
            	logger.error(sb.toString());
            }
        	return false;
        }
    }

    public boolean update(Repository repository, String json, Class tClass) {
        T entity = null;
        boolean updated;
        
        try {
	        entity = (T) new ObjectMapper().readValue(json, tClass);
	
	        updated = repository.update(entity);
	        if (logger.isDebugEnabled()) {
	        	StringBuilder sb = new StringBuilder();
	        	sb.append("update entity instance of ");
	        	sb.append(tClass);
	        	sb.append("from json string {");
	        	sb.append(json);
	        	sb.append("}: ");
	        	sb.append(entity.toString());
	        	logger.debug(sb.toString());
	        }
	        return updated;
        } catch (IOException ex) {

        	return false;
        } catch (HibernateException ex) {
        	if (logger.isErrorEnabled()) {
            	StringBuilder sb = new StringBuilder();
            	sb.append("HibernateException on update entity ");
            	sb.append(entity.toString());
            	sb.append(" instance of ");
            	sb.append(tClass);
            	sb.append(" to repository with message: ");
            	sb.append(ex.getMessage());
            	logger.error(sb.toString());
            }
        	return false;
        }
    }

    private void logJsonException(String json, IOException ex) {
		if (logger.isErrorEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("IOException on read json {");
			sb.append(json);
			sb.append("} with message: ");
			sb.append(ex.getMessage());
			logger.error(sb.toString());
		}
	}
}
