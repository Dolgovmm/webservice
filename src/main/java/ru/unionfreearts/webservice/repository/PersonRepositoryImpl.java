package ru.unionfreearts.webservice.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.unionfreearts.webservice.dbservice.DbService;
import ru.unionfreearts.webservice.dbservice.DbServiceImpl;
import ru.unionfreearts.webservice.dbservice.specification.Specification;
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

    public List<Person> query(Specification<Person> specification) {
        logger.debug("get Person list by query");
        return dbService.query(specification);
    }

    public Person get(long id) {
		logger.debug("get Person entity with id: " + id);
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
