package ru.unionfreearts.webservice.repository;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.unionfreearts.webservice.dbservice.DbService;
import ru.unionfreearts.webservice.dbservice.DbServiceImpl;
import ru.unionfreearts.webservice.dbservice.specification.Specification;
import ru.unionfreearts.webservice.entity.Rank;

import java.util.List;

/**
 * RankRepository implement CRUD methods from interface to work with rank entity.
 * @author M.Dolgov
 * @date 07.05.2017
 */
public class RankRepositoryImpl implements Repository<Rank>{

    static final Logger logger = LoggerFactory.getLogger(SiteRepositoryImpl.class);

    private DbService<Rank> dbService = new DbServiceImpl<>(Rank.class);

    public Rank add(Rank entity) throws HibernateException {
        return dbService.add(entity);
    }

    public Rank get(long id) throws HibernateException{
        return dbService.get(id);
    }

    public boolean remove(Rank entity) throws HibernateException{
        return dbService.remove(entity);
    }

    public boolean update(Rank entity) throws HibernateException{
        return dbService.update(entity);
    }

    public List<Rank> query(Specification<Rank> specification) throws HibernateException {
        return dbService.query(specification);
    }
}
