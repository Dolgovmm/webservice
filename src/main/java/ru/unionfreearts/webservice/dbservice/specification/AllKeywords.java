package ru.unionfreearts.webservice.dbservice.specification;

import ru.unionfreearts.webservice.entity.Keyword;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Михалыч on 14.05.2017.
 */
public class AllKeywords extends AbstractSpacification<Keyword> {
    
    public Predicate toPredicate(Root<Keyword> root, CriteriaBuilder cb) {
        return cb.conjunction();
    }
}
