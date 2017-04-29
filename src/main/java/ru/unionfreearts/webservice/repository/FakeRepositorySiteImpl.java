package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.entity.Site;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Михалыч on 26.04.2017.
 */
public class FakeRepositorySiteImpl implements Repository<Site> {

    private List<Site> list = new ArrayList<Site>();

    public long add(Site entity) {
        list.add(entity);
        return list.indexOf(entity);
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

    public boolean update(Site entity) {
        return true;
    }
}
