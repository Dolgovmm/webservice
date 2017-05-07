package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.entity.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Михалыч on 26.04.2017.
 */
public class FakeRepositoryPageImpl implements Repository<Page> {
    private List<Page> list = new ArrayList<Page>();

    public long add(Page entity) {
        list.add(entity);
        return list.indexOf(entity);
    }

    public List<Page> getAll() {
        return list;
    }

    public Page get(long id) {
        return list.get((int)id);
    }

    public long remove(Page entity) {
        return list.remove(entity) == true? 1 : 0;
    }

    public long update(Page entity) {
        return 1;
    }
}
