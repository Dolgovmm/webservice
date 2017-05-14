package ru.unionfreearts.webservice.dbservice.specification;

import ru.unionfreearts.webservice.entity.Page;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Михалыч on 14.05.2017.
 */
public class AllPage extends AbstractSpacification<Page> {
    
    public Predicate toPredicate(Root<Page> root, CriteriaBuilder cb) {
        return cb.conjunction();
    }
}
