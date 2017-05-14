package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.entity.AbstractEntity;

import java.util.List;

/**
 * Created by Михалыч on 26.04.2017.
 */
public interface Repository<T extends AbstractEntity> {

    public long add(T entity);

    public List<T> getAll();

    public T get(long id);

    public long remove(T entity);

    public long update(T entity);

    public List<T> query();
}
