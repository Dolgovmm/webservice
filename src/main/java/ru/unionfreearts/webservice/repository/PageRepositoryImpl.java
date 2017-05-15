package ru.unionfreearts.webservice.repository;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.unionfreearts.webservice.dbservice.DbService;
import ru.unionfreearts.webservice.dbservice.DbServiceImpl;
import ru.unionfreearts.webservice.dbservice.specification.Specification;
import ru.unionfreearts.webservice.entity.Page;

import java.util.List;

/**
 * PageRepository implement CRUD methods from interface to work with page entity.
 * @author M.Dolgov
 * @date 08.05.2017
 */
public class PageRepositoryImpl implements Repository<Page> {
	static final Logger logger = LoggerFactory.getLogger(PageRepositoryImpl.class);
	
    private DbService<Page> dbService = new DbServiceImpl<Page>(Page.class);

    public long add(Page entity) throws HibernateException {
        logger.debug("add Page entity: " + entity.toString());
		return dbService.add(entity);
    }

    public Page get(long id) throws HibernateException {
        logger.debug("get Page entity with id: " + id);
		return dbService.get(id);
    }

    public long remove(Page entity) throws HibernateException {
		logger.debug("remove Page entity: " + entity.toString());
        return dbService.remove(entity);
    }

    public long update(Page entity) throws HibernateException {
        logger.debug("update Page entity: " + entity.toString());
		return dbService.update(entity);
    }

    @Override
    public List<Page> query(Specification<Page> specification) throws HibernateException {
        logger.debug("get Page list by query");
        return dbService.query(specification);
    }
}
