package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.dbservice.DbService;
import ru.unionfreearts.webservice.dbservice.DbServiceImpl;
import ru.unionfreearts.webservice.entity.Person;

import java.util.List;

/**
 * Created by Михалыч on 08.05.2017.
 */
public class PersonRepositoryImpl implements Repository<Person> {
    private DbService<Person> dbService = new DbServiceImpl<Person>(Person.class);

    public long add(Person entity) {
        return dbService.add(entity);
    }

    public List<Person> getAll() {
        return dbService.getAll();
    }

    public Person get(long id) {
        return dbService.get(id);
    }

    public long remove(Person entity) {
        return dbService.remove(entity);
    }

    public long update(Person entity) {
        return dbService.update(entity);
    }
}
