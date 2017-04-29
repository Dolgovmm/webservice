package ru.unionfreearts.webservice.repository;

import java.util.List;

/**
 * Created by Михалыч on 26.04.2017.
 */
public interface Repository<T> {

    public long add(T entity);

    public List<T> getAll();

    public T get(long id);

    public boolean remove(T entity);

    public boolean update(T entity);
}
