package ru.unionfreearts.webservice.dbservice.specification;

import ru.unionfreearts.webservice.entity.Keyword;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Class to get all keyword from table DB.
 * @author M.Dolgov
 * @date 14.05.2017
 */
public class AllKeywords extends AbstractSpecification<Keyword> {

    public Predicate toPredicate(Root<Keyword> root, CriteriaBuilder cb) {
        return cb.conjunction();
    }
}
