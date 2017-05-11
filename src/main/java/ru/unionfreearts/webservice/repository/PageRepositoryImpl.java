package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.dbservice.DbService;
import ru.unionfreearts.webservice.dbservice.DbServiceImpl;
import ru.unionfreearts.webservice.entity.Page;

import java.util.List;

/**
 * Created by Михалыч on 08.05.2017.
 */
public class PageRepositoryImpl implements Repository<Page> {
	static final Logger logger = LoggerFactory.getLogger(PageRepositoryImpl.class);
	
    private DbService<Page> dbService = new DbServiceImpl<Page>(Page.class);

    public long add(Page entity) {
        logger.debug("add Page entity: " + entity.toString());
		return dbService.add(entity);
    }

    public List<Page> getAll() {
		logger.debug("get all pages from table");
        return dbService.getAll();
    }

    public Page get(long id) {
        logger.debug("get Page entity with id: " + id.toString());
		return dbService.get(id);
    }

    public long remove(Page entity) {
		logger.debug("remove Page entity: " + entity.toString());
        return dbService.remove(entity);
    }

    public long update(Page entity) {
        logger.debug("update Page entity: " + entity.toString());
		return dbService.update(entity);
    }
}
