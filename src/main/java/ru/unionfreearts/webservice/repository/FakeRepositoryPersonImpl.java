package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Михалыч on 26.04.2017.
 */
public class FakeRepositoryPersonImpl implements Repository<Person> {
    private List<Person> list = new ArrayList<Person>();

    public boolean add(Person entity) {
        return list.add(entity);
    }

    public List<Person> getAll() {
        return list;
    }

    public Person get(int id) {
        return list.get(id);
    }

    public boolean remove(Person entity) {
        return list.remove(entity);
    }

    public boolean set(Person entity) {
        return true;
    }
}
