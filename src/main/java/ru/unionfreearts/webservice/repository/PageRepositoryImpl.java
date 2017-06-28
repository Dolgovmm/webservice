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

    public Page add(Page entity) throws HibernateException {
		return dbService.add(entity);
    }

    public Page get(long id) throws HibernateException {
		return dbService.get(id);
    }

    public boolean remove(Page entity) throws HibernateException {
        return dbService.remove(entity);
    }

    public boolean update(Page entity) throws HibernateException {
		return dbService.update(entity);
    }

    @Override
    public List<Page> query(Specification<Page> specification) throws HibernateException {
        return dbService.query(specification);
    }
}
