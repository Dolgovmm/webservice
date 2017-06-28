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

    public Person add(Person entity) throws HibernateException {
        return dbService.add(entity);
    }

    public List<Person> query(Specification<Person> specification) throws HibernateException {
        return dbService.query(specification);
    }

    public Person get(long id) throws HibernateException {
        return dbService.get(id);
    }

    public boolean remove(Person entity) throws HibernateException {
        return dbService.remove(entity);
    }

    public boolean update(Person entity) throws HibernateException {
        return dbService.update(entity);
    }
}
