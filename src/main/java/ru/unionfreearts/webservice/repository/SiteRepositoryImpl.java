package ru.unionfreearts.webservice.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.unionfreearts.webservice.dbservice.DbService;
import ru.unionfreearts.webservice.dbservice.DbServiceImpl;
import ru.unionfreearts.webservice.entity.Site;

import java.util.List;

/**
 * Created by Михалыч on 07.05.2017.
 */
public class SiteRepositoryImpl implements Repository<Site> {

    static final Logger logger = LoggerFactory.getLogger(SiteRepositoryImpl.class);

    private DbService<Site> dbService = new DbServiceImpl<Site>(Site.class);

    public long add(Site entity) {
        logger.debug("add site entity: " + entity.toString());
        return dbService.add(entity);
    }

    public List<Site> getAll() {
        return dbService.getAll();
    }

    public Site get(long id) {
        return dbService.get(id);
    }

    public long remove(Site entity) {
        return dbService.remove(entity);
    }

    public long update(Site entity) {
        return dbService.update(entity);
    }
}
