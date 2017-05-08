package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.dbservice.DbService;
import ru.unionfreearts.webservice.dbservice.DbServiceImpl;
import ru.unionfreearts.webservice.entity.Keyword;

import java.util.List;

/**
 * Created by Михалыч on 08.05.2017.
 */
public class KeywordRepositoryImpl implements Repository<Keyword> {
    private DbService<Keyword> dbService = new DbServiceImpl<Keyword>(Keyword.class);

    public long add(Keyword entity) {
        return dbService.add(entity);
    }

    public List<Keyword> getAll() {
        return dbService.getAll();
    }

    public Keyword get(long id) {
        return dbService.get(id);
    }

    public long remove(Keyword entity) {
        return dbService.remove(entity);
    }

    public long update(Keyword entity) {
        return dbService.update(entity);
    }
}
