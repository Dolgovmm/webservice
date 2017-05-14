package ru.unionfreearts.webservice.dbservice.specification;

import ru.unionfreearts.webservice.entity.Person;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Михалыч on 14.05.2017.
 */
public class AllPerson extends AbstractSpacification<Person> {

    @Override
    public Predicate toPredicate(Root<Person> root, CriteriaBuilder cb) {
        return cb.conjunction();
    }
}
