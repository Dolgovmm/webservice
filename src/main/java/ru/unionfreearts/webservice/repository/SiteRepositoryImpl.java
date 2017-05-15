package ru.unionfreearts.webservice.repository;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private DbService<Site> dbService = new DbServiceImpl<>(Site.class);

    public long add(Site entity) throws HibernateException {
        logger.debug("add site entity: " + entity.toString());
        return dbService.add(entity);
    }

    public Site get(long id) throws HibernateException {
		logger.debug("get site entity with id: " + id);
        return dbService.get(id);
    }

    public long remove(Site entity) throws HibernateException {
		logger.debug("remove site entity: " + entity.toString());
        return dbService.remove(entity);
    }

    public long update(Site entity) throws HibernateException {
		logger.debug("update site entity: " + entity.toString());
        return dbService.update(entity);
    }

    @Override
    public List<Site> query(Specification<Site> specification) throws HibernateException {
        logger.debug("get site list by query");
        return dbService.query(specification);
    }
}
