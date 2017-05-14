package ru.unionfreearts.webservice.dbservice.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Михалыч on 14.05.2017.
 */
public interface Specification<T> {

    public Predicate toPredicate(Root<T> root, CriteriaBuilder cb);

    public Class<T> getType();
}
