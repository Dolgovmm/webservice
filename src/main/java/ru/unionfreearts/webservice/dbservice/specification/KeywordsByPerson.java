package ru.unionfreearts.webservice.dbservice.specification;

import ru.unionfreearts.webservice.entity.Keyword;
import ru.unionfreearts.webservice.entity.Keyword_;
import ru.unionfreearts.webservice.entity.Person_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Михалыч on 14.05.2017.
 */
public class KeywordsByPerson extends AbstractSpacification<Keyword> {
    private long personId;

    public KeywordsByPerson(long personId) {
        this.personId = personId;
    }

    @Override
    public Predicate toPredicate(Root<Keyword> root, CriteriaBuilder cb) {
        Path<Long> personIdPath = root.get(Keyword_.person).get(Person_.id);
        return cb.equal(personIdPath, personId);
    }
}
