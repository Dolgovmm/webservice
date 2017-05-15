package ru.unionfreearts.webservice.repository;

import ru.unionfreearts.webservice.dbservice.specification.Specification;
import ru.unionfreearts.webservice.entity.AbstractEntity;

import java.util.List;

/**
 * Repository interface implement CRUD methods to work with any entity.
 * @author M.Dolgov
 * @date 26.04.2017
 */
public interface Repository<T extends AbstractEntity> {

    public long add(T entity);

    public T get(long id);

    public long remove(T entity);

    public long update(T entity);

    public List<T> query(Specification<T> specification);
}
