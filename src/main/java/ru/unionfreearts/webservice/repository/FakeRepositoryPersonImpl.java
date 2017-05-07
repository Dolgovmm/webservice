package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Михалыч on 26.04.2017.
 */
public class FakeRepositoryPersonImpl implements Repository<Person> {
    private List<Person> list = new ArrayList<Person>();

    public long add(Person entity) {
        list.add(entity);
        return list.indexOf(entity);
    }

    public List<Person> getAll() {
        return list;
    }

    public Person get(long id) {
        return list.get((int)id);
    }

    public long remove(long id) {
        return list.remove(id) == true? 1 : 0;
    }

    public long update(Person entity) {
        return 1;
    }
}
