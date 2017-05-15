package ru.unionfreearts.webservice.repository;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.unionfreearts.webservice.dbservice.DbService;
import ru.unionfreearts.webservice.dbservice.DbServiceImpl;
import ru.unionfreearts.webservice.dbservice.specification.Specification;
import ru.unionfreearts.webservice.entity.Person;

import java.util.List;

/**
 * PersonRepository implement CRUD methods from interface to work with person entity.
 * @author M.Dolgov
 * @date 08.05.2017
 */
public class PersonRepositoryImpl implements Repository<Person> {
	static final Logger logger = LoggerFactory.getLogger(PersonRepositoryImpl.class);
	
    private DbService<Person> dbService = new DbServiceImpl<Person>(Person.class);

    public long add(Person entity) throws HibernateException {
		logger.debug("add Person entity: " + entity.toString());
        return dbService.add(entity);
    }

    public List<Person> query(Specification<Person> specification) throws HibernateException {
        logger.debug("get Person list by query");
        return dbService.query(specification);
    }

    public Person get(long id) throws HibernateException {
		logger.debug("get Person entity with id: " + id);
        return dbService.get(id);
    }

    public long remove(Person entity) throws HibernateException {
		logger.debug("remove Person entity: " + entity.toString());
        return dbService.remove(entity);
    }

    public long update(Person entity) throws HibernateException {
		logger.debug("update Person entity: " + entity.toString());
        return dbService.update(entity);
    }
}
