package ru.unionfreearts.webservice.repository;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.unionfreearts.webservice.dbservice.DbService;
import ru.unionfreearts.webservice.dbservice.DbServiceImpl;
import ru.unionfreearts.webservice.dbservice.specification.Specification;
import ru.unionfreearts.webservice.entity.Keyword;

import java.util.List;

/**
 * KeywordRepository implement CRUD methods from interface to work with keyword entity.
 * @author M.Dolgov
 * @date 08.05.2017
 */
public class KeywordRepositoryImpl implements Repository<Keyword> {
	static final Logger logger = LoggerFactory.getLogger(KeywordRepositoryImpl.class);
	
    private DbService<Keyword> dbService = new DbServiceImpl<Keyword>(Keyword.class);

    public long add(Keyword entity) throws HibernateException {
        logger.debug("add Keyword entity: " + entity.toString());
		return dbService.add(entity);
    }

    public Keyword get(long id) throws HibernateException {
        logger.debug("get Keyword entity with id: " + id);
		return dbService.get(id);
    }

    public long remove(Keyword entity) throws HibernateException {
        logger.debug("remove Keyword entity: " + entity.toString());
		return dbService.remove(entity);
    }

    public long update(Keyword entity) throws HibernateException {
        logger.debug("update Keyword entity: " + entity.toString());
		return dbService.update(entity);
    }

    public List<Keyword> query(Specification<Keyword> specification) throws HibernateException {
        logger.debug("get Keyword list by query");
        return dbService.query(specification);
    }
}
