package ru.unionfreearts.webservice.dbservice.specification;

import ru.unionfreearts.webservice.entity.Person;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Class to get all person from table DB.
 * @author M.Dolgov
 * @date 14.05.2017
 */
public class AllPerson extends AbstractSpecification<Person> {

    @Override
    public Predicate toPredicate(Root<Person> root, CriteriaBuilder cb) {
        return cb.conjunction();
    }
}
