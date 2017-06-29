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
    private List<Person> list;

    public FakeRepositoryPersonImpl() {
        list = new ArrayList<>();
    }

    public Person add(Person entity) {
    	list.add(entity);
        entity.setId(list.indexOf(entity));
        return entity;
    }

    public List<Person> getAll() {
        return list;
    }

    public Person get(long id) {
        return list.get((int)id);
    }

    public boolean remove(Person entity) {
        return list.remove(entity);
    }

    public boolean update(Person entity) {
    	if (list.size() < entity.getId()) {
        	list.set((int)entity.getId(), entity);
        	return true;
        } else {
        	return false;
        }
    }

    @Override
    public List<Person> query(Specification<Person> specification) {
        return null;
    }
}
