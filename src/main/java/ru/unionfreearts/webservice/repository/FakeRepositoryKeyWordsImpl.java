package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.entity.Keyword;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Михалыч on 26.04.2017.
 */
public class FakeRepositoryKeyWordsImpl implements Repository<Keyword>{
    private List<Keyword> list = new ArrayList<Keyword>();

    public long add(Keyword entity) {
        list.add(entity);
        return list.indexOf(entity);
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
        return true;
    }
}
