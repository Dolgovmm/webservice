package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.dbservice.specification.Specification;
import ru.unionfreearts.webservice.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implement fake repository to test.
 * @author M.Dolgov
 * @date 26.04.2017
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

    public long remove(Person entity) {
        return list.remove(entity) == true? 1 : 0;
    }

    public long update(Person entity) {
        return 1;
    }

    @Override
    public List<Person> query(Specification<Person> specification) {
        return null;
    }
}
