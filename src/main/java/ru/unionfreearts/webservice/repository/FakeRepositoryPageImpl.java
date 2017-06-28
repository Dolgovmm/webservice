package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.dbservice.specification.Specification;
import ru.unionfreearts.webservice.entity.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implement fake repository to test.
 * @author M.Dolgov
 * @date 26.04.2017
 */
public class FakeRepositoryPageImpl implements Repository<Page> {
    private List<Page> list = new ArrayList<Page>();

    public Page add(Page entity) {
    	list.add(entity);
        entity.setId(list.indexOf(entity));
        return entity;
    }

    public List<Page> getAll() {
        return list;
    }

    public Page get(long id) {
        return list.get((int)id);
    }

    public boolean remove(Page entity) {
        return list.remove(entity);
    }

    public boolean update(Page entity) {
    	if (list.size() < entity.getId()) {
        	list.set((int)entity.getId(), entity);
        	return true;
        } else {
        	return false;
        }
    }

    @Override
    public List<Page> query(Specification<Page> specification) {
        return null;
    }
}
