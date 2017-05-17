package ru.unionfreearts.webservice.dbservice.specification;

import ru.unionfreearts.webservice.entity.Page;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Class to get all pages from table DB.
 * @author M.Dolgov
 * @date 14.05.2017
 */
public class AllPage extends AbstractSpecification<Page> {

    public Predicate toPredicate(Root<Page> root, CriteriaBuilder cb) {
        return cb.conjunction();
    }
}
