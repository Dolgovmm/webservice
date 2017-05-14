package ru.unionfreearts.webservice.dbservice.specification;

import ru.unionfreearts.webservice.entity.Site;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Михалыч on 14.05.2017.
 */
public class AllSites extends AbstractSpacification<Site> {
    @Override
    public Predicate toPredicate(Root<Site> root, CriteriaBuilder cb) {
        return cb.conjunction();
    }
}
