package ru.unionfreearts.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;
import java.util.List;

/**
 * Created by Михалыч on 11.05.2017.
 */
public class AnyController<T> {
    static final Logger logger = LoggerFactory.getLogger(AnyController.class);

    public long add(Repository repository, String json, Class tClass){
        T entity;
        long id;
        logger.debug("create empty Site instance");
        try {
            entity = (T) new ObjectMapper().readValue(json, tClass);
            logger.debug("get value Site instance from json");
            id = repository.add(entity);
            logger.debug("add site instance to repository: " + entity.toString());
        } catch (IOException ex) {
            logger.error("IOException on read json " + json + " with messsage: " + ex.getMessage());
            id = -1l;
        }
        return id;
    }

    public T getById(Repository repository, long id){
        T entity;
        logger.debug("create empty entity instance");
        entity = (T)repository.get(id);
        logger.debug("get site instance from repository: " + entity.toString());
        return entity;
    }

    public List<T> getAll(Repository repository){
        List<T> list = repository.getAll();
        return list;
    }

    public long remove(Repository repository, String json, Class tClass){
        T entity;
        logger.debug("create empty Site instance");
        long removed;
        try{
            entity = (T) new ObjectMapper().readValue(json, tClass);
            logger.debug("get value Site instance from json");
            removed = repository.remove(entity);
            logger.debug("remove site from repository: " + entity.toString());
        }catch (IOException ex){
            logger.error("IOException on read json " + json + " with messsage: " + ex.getMessage());
            removed = -1l;
            logger.debug("set empty responseEntity and status BAD_REQUEST");
        }
        return removed;
    }

    public long update(Repository repository, String json, Class tClass){
        T entity;
        logger.debug("create empty Site instance");
        long updated;
        try{
            entity = (T) new ObjectMapper().readValue(json, tClass);
            logger.debug("get value Site instance from json");
            updated = repository.update(entity);
            logger.debug("update site on repository: " + entity.toString());
        }catch (IOException ex){
            logger.error("IOException on read json " + json + " with messsage: " + ex.getMessage());
            updated = -1;
            logger.debug("set empty responseEntity and status BAD_REQUEST");
        }
        return updated;
    }
}
