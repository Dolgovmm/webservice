package ru.unionfreearts.webservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "personpagerank")
public class Rank extends AbstractEntity implements Serializable {
    @Id
    @ManyToOne(targetEntity = Person.class)
    private Person person;
    @Id
    @JsonIgnore
    @ManyToOne(targetEntity = Page.class)
    private Page page;
    @Column(name = "rank")
    private Integer rank;

    public Rank() {
    }

    public Rank(Person person, Page page, Integer rank) {
        this.person = person;
        this.page = page;
        this.rank = rank;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Rank rank = (Rank) obj;

        if (!person.equals(rank.person)) return false;
        return page.equals(rank.page);
    }

    @Override
    public int hashCode() {
        int result = person.hashCode();
        result = 31 * result + page.hashCode();
        return result;
    }
}