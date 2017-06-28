package ru.unionfreearts.webservice.dbservice;

import ru.unionfreearts.webservice.dbservice.specification.Specification;
import ru.unionfreearts.webservice.entity.AbstractEntity;

import java.util.List;

/**
 * DbService implement CRUD methods to work with database.
 * @author M.Dolgov
 * @date 30.04.2017
 */
public interface DbService<T> {

    public T add(T entity);

    public T get(long id);

    public boolean remove(T entity);

    public boolean update(T entity);

    public List<T> query(Specification<T> specification);
}
