package ru.unionfreearts.webservice.repository;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.unionfreearts.webservice.dbservice.DbService;
import ru.unionfreearts.webservice.dbservice.DbServiceImpl;
import ru.unionfreearts.webservice.dbservice.specification.Specification;
import ru.unionfreearts.webservice.entity.Site;

import java.util.List;

/**
 * SiteRepository implement CRUD methods from interface to work with site entity.
 * @author M.Dolgov
 * @date 07.05.2017
 */
public class SiteRepositoryImpl implements Repository<Site> {
    static final Logger logger = LoggerFactory.getLogger(SiteRepositoryImpl.class);
    
    private DbService<Site> dbService;

    public SiteRepositoryImpl() {
        this.dbService = new DbServiceImpl<>(Site.class);
    }

    public Site add(Site entity) throws HibernateException {
        return dbService.add(entity);
    }

    public Site get(long id) throws HibernateException {
        return dbService.get(id);
    }

    public boolean remove(Site entity) throws HibernateException {
        return dbService.remove(entity);
    }

    public boolean update(Site entity) throws HibernateException {
        return dbService.update(entity);
    }

    @Override
    public List<Site> query(Specification<Site> specification) throws HibernateException {
        return dbService.query(specification);
    }
}
