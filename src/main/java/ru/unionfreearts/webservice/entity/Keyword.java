package ru.unionfreearts.webservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "keywords")
public class Keyword extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "name", length = 248, nullable = false, unique = true)
    private String name;
    @ManyToOne(targetEntity = Person.class)
    private Person person;

    public Keyword() {
    }

    public Keyword(long id, String name, Person person) {
        this.id = id;
        this.name = name;
        this.person = person;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Keyword otherKeyword = (Keyword) obj;
        if ((this.id == otherKeyword.id) && (this.name.equals(otherKeyword.name))){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "keyword: id = " + id + ", name = " + name;
    }
}
