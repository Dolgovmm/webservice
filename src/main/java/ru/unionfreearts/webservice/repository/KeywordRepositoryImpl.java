package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.dbservice.DbService;
import ru.unionfreearts.webservice.dbservice.DbServiceImpl;
import ru.unionfreearts.webservice.entity.Keyword;

import java.util.List;

/**
 * Created by Михалыч on 08.05.2017.
 */
public class KeywordRepositoryImpl implements Repository<Keyword> {
	static final Logger logger = LoggerFactory.getLogger(KeywordRepositoryImpl.class);
	
    private DbService<Keyword> dbService = new DbServiceImpl<Keyword>(Keyword.class);

    public long add(Keyword entity) {
        logger.debug("add Keyword entity: " + entity.toString());
		return dbService.add(entity);
    }

    public List<Keyword> getAll() {
        logger.debug("get all keywords from table");
		return dbService.getAll();
    }

    public Keyword get(long id) {
        logger.debug("get Keyword entity with id: " + id.toString());
		return dbService.get(id);
    }

    public long remove(Keyword entity) {
        logger.debug("remove Keyword entity: " + entity.toString());
		return dbService.remove(entity);
    }

    public long update(Keyword entity) {
        logger.debug("update Keyword entity: " + entity.toString());
		return dbService.update(entity);
    }
}
