package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.dbservice.specification.Specification;
import ru.unionfreearts.webservice.entity.Keyword;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implement fake repository to test.
 * @author M.Dolgov
 * @date 26.04.2017
 */
public class FakeRepositoryKeyWordsImpl implements Repository<Keyword>{
    private List<Keyword> list;

    public FakeRepositoryKeyWordsImpl() {
        list = new ArrayList<>();
    }

    public Keyword add(Keyword entity) {
    	list.add(entity);
        entity.setId(list.indexOf(entity));
        return entity;
    }

    public List<Keyword> getAll() {
        return list;
    }

    public Keyword get(long id) {
        return list.get((int)id);
    }

    public boolean remove(Keyword entity) {
        return list.remove(entity);
    }

    public boolean update(Keyword entity) {
    	if (list.size() < entity.getId()) {
        	list.set((int)entity.getId(), entity);
        	return true;
        } else {
        	return false;
        }
    }

    public List<Keyword> query(Specification<Keyword> specification) {
        return null;
    }
}
