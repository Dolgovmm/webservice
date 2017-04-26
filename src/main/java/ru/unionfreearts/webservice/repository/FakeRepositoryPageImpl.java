package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.entity.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Михалыч on 26.04.2017.
 */
public class FakeRepositoryPageImpl implements Repository<Page> {
    private List<Page> list = new ArrayList<Page>();

    public boolean add(Page entity) {
        return list.add(entity);
    }

    public List<Page> getAll() {
        return list;
    }

    public Page get(int id) {
        return list.get(id);
    }

    public boolean remove(Page entity) {
        return list.remove(entity);
    }

    public boolean set(Page entity) {
        return true;
    }
}
