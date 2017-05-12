package ru.unionfreearts.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.unionfreearts.webservice.entity.AbstractEntity;
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;
import java.util.List;

/**
 * Created by Михалыч on 11.05.2017.
 */
public class AnyController<T extends AbstractEntity> {
    static final Logger logger = LoggerFactory.getLogger(AnyController.class);

    public long add(Repository repository, String json, Class tClass) throws IOException, HibernateException{
        T entity;
        logger.debug("create empty entity instance");
        long id;
        entity = (T) new ObjectMapper().readValue(json, tClass);
        logger.debug("get value entity instance from json");
        id = repository.add(entity);
        logger.debug("add entity instance to repository: " + entity.toString());
        return id;
    }

    public T getById(Repository repository, long id) throws HibernateException{
        T entity;
        logger.debug("create empty entity instance");
        entity = (T)repository.get(id);
        logger.debug("get site instance from repository: " + entity.toString());
        return entity;
    }

    public List<T> getAll(Repository repository) throws HibernateException{
        List<T> list = repository.getAll();
        return list;
    }

    public long remove(Repository repository, String json, Class tClass) throws IOException, HibernateException{
        T entity;
        logger.debug("create empty entity instance");
        long removed;
        entity = (T) new ObjectMapper().readValue(json, tClass);
        logger.debug("get value entity instance from json");
        removed = repository.remove(entity);
        logger.debug("remove entity from repository: " + entity.toString());
        return removed;
    }

    public long update(Repository repository, String json, Class tClass) throws IOException, HibernateException{
        T entity;
        logger.debug("create empty entity instance");
        long updated;
        entity = (T) new ObjectMapper().readValue(json, tClass);
        logger.debug("get value entity instance from json");
        updated = repository.update(entity);
        logger.debug("update entity on repository: " + entity.toString());
        return updated;
    }
}
