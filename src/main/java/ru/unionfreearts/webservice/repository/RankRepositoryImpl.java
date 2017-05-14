package ru.unionfreearts.webservice.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.unionfreearts.webservice.dbservice.DbService;
import ru.unionfreearts.webservice.dbservice.DbServiceImpl;
import ru.unionfreearts.webservice.entity.Rank;

import java.util.List;

/**
 * Created by Михалыч on 14.05.2017.
 */
public class RankRepositoryImpl implements Repository<Rank>{

    static final Logger logger = LoggerFactory.getLogger(SiteRepositoryImpl.class);

    private DbService<Rank> dbService = new DbServiceImpl<>(Rank.class);

    public long add(Rank entity) {
        logger.debug("add rank entity: " + entity.toString());
        return dbService.add(entity);
    }

    @Override
    public List<Rank> getAll() {
        logger.debug("get all ranks from table");
        return dbService.getAll();
    }

    @Override
    public Rank get(long id) {
        logger.debug("get rank entity with id: " + id);
        return dbService.get(id);
    }

    @Override
    public long remove(Rank entity) {
        logger.debug("remove rank entity: " + entity.toString());
        return dbService.remove(entity);
    }

    @Override
    public long update(Rank entity) {
        logger.debug("update rank entity: " + entity.toString());
        return dbService.update(entity);
    }

    @Override
    public List<Rank> query() {
        return null;
    }
}
