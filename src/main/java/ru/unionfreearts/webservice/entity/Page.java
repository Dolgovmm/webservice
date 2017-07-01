package ru.unionfreearts.webservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "pages")
public class Page extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "url", length = 248, nullable = false, unique = true)
    private String url;
    @ManyToOne(targetEntity = Site.class)
    private Site site;
    @Column(name = "found_datetime")
    private Date foundDateTime;
    @Column(name = "last_datetime")
    private Date lastDateTime;
    @JsonIgnore
    @OneToMany(targetEntity = Rank.class, mappedBy = "page")
    private Set<Rank> ranks;

    public Page() {
    }

    public Page(long id, String url, Site site, Date foundDateTime, Date lastDateTime) {
        this.id = id;
        this.url = url;
        this.site = site;
        this.foundDateTime = foundDateTime;
        this.lastDateTime = lastDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Date getFoundDateTime() {
        return foundDateTime;
    }

    public void setFoundDateTime(Date foundDateTime) {
        this.foundDateTime = foundDateTime;
    }

    public Date getLastDateTime() {
        return lastDateTime;
    }

    public void setLastDateTime(Date lastDateTime) {
        this.lastDateTime = lastDateTime;
    }

    public Set<Rank> getRanks() {
        return ranks;
    }

    public void setRanks(Set<Rank> ranks) {
        this.ranks = ranks;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Page otherPage = (Page) obj;
        if ((this.id == otherPage.id) && (this.url.equals(otherPage.url))){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "page: id = " + id + ", url = " + url;
    }
}
