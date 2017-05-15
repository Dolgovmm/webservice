package ru.unionfreearts.webservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "sites")
public class Site extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "name", length = 248, nullable = false, unique = true)
    private String name;
    @JsonIgnore
    @OneToMany(targetEntity = Page.class, mappedBy = "site")
    private Set<Page> pages;

    public Site() {
    }

    public Site(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Site(String name){
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Page> getPages() {
        return pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object obj) {
        Site otherSite = (Site) obj;
        if ((this.id == otherSite.id) && (this.name.equals(otherSite.name))){
            return true;
        }
        return false;
    }
}
