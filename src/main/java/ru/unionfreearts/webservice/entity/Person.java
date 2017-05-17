package ru.unionfreearts.webservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "name", length = 248, nullable = false, unique = true)
    private String name;
    @JsonIgnore
    @OneToMany(targetEntity = Keyword.class, mappedBy = "person")
    private Set<Keyword> keywords;
    @JsonIgnore
    @OneToMany(targetEntity = Rank.class, mappedBy = "person")
    private Set<Rank> ranks;

    public Person() {
    }

    public Person(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
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

    public Set<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<Keyword> keywords) {
        this.keywords = keywords;
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

        Person otherPerson = (Person) obj;
        if ((this.id == otherPerson.id) && (this.name.equals(otherPerson.name))){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "person: id = " + id + ", name = " + name;
    }
}
