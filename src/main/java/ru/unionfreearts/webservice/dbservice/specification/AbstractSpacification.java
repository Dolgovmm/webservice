package ru.unionfreearts.webservice.dbservice.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Михалыч on 14.05.2017.
 */
public class AbstractSpacification<T> implements Specification<T> {

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaBuilder cb) {
        return null;
    }

    @Override
    public Class<T> getType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }
}
