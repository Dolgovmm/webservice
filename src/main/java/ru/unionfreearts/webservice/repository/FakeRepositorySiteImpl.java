package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.dbservice.specification.Specification;
import ru.unionfreearts.webservice.entity.Site;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implement fake repository to test.
 * @author M.Dolgov
 * @date 26.04.2017
 */
public class FakeRepositorySiteImpl implements Repository<Site> {

    private List<Site> list;

    public FakeRepositorySiteImpl() {
        list = new ArrayList<>();
    }

    public Site add(Site entity) {
        list.add(entity);
        entity.setId(list.indexOf(entity));
        return entity;
    }

    public List<Site> getAll() {
        return list;
    }

    public Site get(long id) {
        return list.get(((int) id));
    }

    public boolean remove(Site entity) {
        return list.remove(entity);
    }

    @Override
    public List<Site> query(Specification<Site> specification) {
        return null;
    }

    public boolean update(Site entity) {
        if (list.size() < entity.getId()) {
        	list.set((int)entity.getId(), entity);
        	return true;
        } else {
        	return false;
        }
        
    }
}
