package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.dbservice.DbService;
import ru.unionfreearts.webservice.dbservice.DbServiceImpl;
import ru.unionfreearts.webservice.entity.Person;

import java.util.List;

/**
 * Created by Михалыч on 08.05.2017.
 */
public class PersonRepositoryImpl implements Repository<Person> {
	static final Logger logger = LoggerFactory.getLogger(PersonRepositoryImpl.class);
	
    private DbService<Person> dbService = new DbServiceImpl<Person>(Person.class);

    public long add(Person entity) {
		logger.debug("add Person entity: " + entity.toString());
        return dbService.add(entity);
    }

    public List<Person> getAll() {
		logger.debug("get all persons from table");
        return dbService.getAll();
    }

    public Person get(long id) {
		logger.debug("get Person entity with id: " + id.toString());
        return dbService.get(id);
    }

    public long remove(Person entity) {
		logger.debug("remove Person entity: " + entity.toString());
        return dbService.remove(entity);
    }

    public long update(Person entity) {
		logger.debug("update Person entity: " + entity.toString());
        return dbService.update(entity);
    }
}
