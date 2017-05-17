package ru.unionfreearts.webservice.dbservice.specification;

import ru.unionfreearts.webservice.entity.Site;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Class to get all sites from table DB.
 * @author M.Dolgov
 * @date 14.05.2017
 */
public class AllSites extends AbstractSpecification<Site> {
    @Override
    public Predicate toPredicate(Root<Site> root, CriteriaBuilder cb) {
        return cb.conjunction();
    }
}
