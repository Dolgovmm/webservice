package ru.unionfreearts.webservice.dbService;

import java.util.List;

/**
 * Created by Михалыч on 30.04.2017.
 */
public interface DbService<T> {

    public long add(T entity);

    public T get(long id);

    public List<T> getAll();

    public int remove(long id);

    public int update(T entity);
}
