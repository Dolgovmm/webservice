package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.dbservice.DbService;
import ru.unionfreearts.webservice.dbservice.DbServiceImpl;
import ru.unionfreearts.webservice.entity.Page;

import java.util.List;

/**
 * Created by Михалыч on 08.05.2017.
 */
public class PageRepositoryImpl implements Repository<Page> {
    private DbService<Page> dbService = new DbServiceImpl<Page>(Page.class);

    public long add(Page entity) {
        return dbService.add(entity);
    }

    public List<Page> getAll() {
        return dbService.getAll();
    }

    public Page get(long id) {
        return dbService.get(id);
    }

    public long remove(Page entity) {
        return dbService.remove(entity);
    }

    public long update(Page entity) {
        return dbService.update(entity);
    }
}
