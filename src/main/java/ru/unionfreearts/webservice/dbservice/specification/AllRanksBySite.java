package ru.unionfreearts.webservice.dbservice.specification;

import ru.unionfreearts.webservice.entity.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Михалыч on 14.05.2017.
 */
public class AllRanksBySite extends AbstractSpacification<Rank> {
    private long siteId;

    public AllRanksBySite(long siteId) {
        this.siteId = siteId;
    }

    @Override
    public Predicate toPredicate(Root<Rank> root, CriteriaBuilder cb) {
        Path<Page> page = root.get(Rank_.page);
        Path<Site> site = page.get(Page_.site);
        Path<Long> id = site.get(Site_.id);
        return cb.equal(id, siteId);
    }
}
