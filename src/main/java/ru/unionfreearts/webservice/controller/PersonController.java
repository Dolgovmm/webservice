package ru.unionfreearts.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.entity.Person;
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;
import java.util.List;

/**
 * Created by Михалыч on 30.04.2017.
 */
@Controller
@RequestMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)// + "; charset = UTF-8")
public class PersonController {

    @Autowired
    @Qualifier("personRepository")
    Repository repository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> addPerson(@RequestBody String json) {
        Person person = null;
        ResponseEntity<Long> response;
        try {
            person = new ObjectMapper().readValue(json, Person.class);
            person.setId(repository.add(person));
            response = new ResponseEntity<Long>(person.getId(), HttpStatus.OK);
        } catch (IOException e) {
            response = new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Person> getPersonById(@PathVariable long id){
        Person person = null;
        ResponseEntity<Person> response;
        person = (Person)repository.get(id);
        if (person != null) {
            response = new ResponseEntity<Person>(person, HttpStatus.OK);
        }else{
            response = new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
            return response;
        }
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Person>> getPersonList(){
        ResponseEntity<List<Person>> response = new ResponseEntity<List<Person>>(repository.getAll(), HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Long> removePerson(@PathVariable long id){
        Person person = null;
        ResponseEntity<Long> response;
        long removed = repository.remove(id);
        response = new ResponseEntity<Long>(removed, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Long> updatePerson(@RequestBody String json){
        Person person = null;
        ResponseEntity<Long> response;
        try{
            person = new ObjectMapper().readValue(json, Person.class);
            long updated = repository.update(person);
            response = new ResponseEntity<Long>(updated, HttpStatus.OK);
        }catch (IOException ex){
            response = new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
            return response;
        }
        return response;
    }
}
