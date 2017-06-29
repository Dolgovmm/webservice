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
	
    private DbService<Keyword> dbService;

    public KeywordRepositoryImpl() {
        dbService = new DbServiceImpl<>(Keyword.class);
    }

    public Keyword add(Keyword entity) throws HibernateException {
		return dbService.add(entity);
    }

    public Keyword get(long id) throws HibernateException {
		return dbService.get(id);
    }

    public boolean remove(Keyword entity) throws HibernateException {
		return dbService.remove(entity);
    }

    public boolean update(Keyword entity) throws HibernateException {
		return dbService.update(entity);
    }

    public List<Keyword> query(Specification<Keyword> specification) throws HibernateException {
        return dbService.query(specification);
    }
}
