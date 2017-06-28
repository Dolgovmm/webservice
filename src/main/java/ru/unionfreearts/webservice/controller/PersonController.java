package ru.unionfreearts.webservice.controller;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.dbservice.specification.AllPerson;
import ru.unionfreearts.webservice.entity.Person;
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PersonController working with RESTFull requests. He execute CRUD methods with person entity.
 * @author M.Dolgov
 * @date 03.04.2017
 */
@Controller
@RequestMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)// + "; charset = UTF-8")
public class PersonController {
	static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    @Qualifier("personRepository")
    private Repository repository;

    private AnyController<Person> controller = new AnyController<>();

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Person> addPerson(@RequestBody String json) {
		Person person = null;
		
		person = controller.add(repository, json, Person.class);
		
		return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Person> getPersonById(@PathVariable long id){
    	
		Person person = controller.getById(repository, id, Person.class);
		
		return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Person>> getPersonList(){
		List<Person> list = null;
		
		list = controller.getAll(repository, new AllPerson(), Person.class);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Person> removePerson(@RequestBody String json){
    	boolean removed;
    	
		removed = controller.remove(repository, json, Person.class);
		
		if (removed) {
        	return new ResponseEntity<>(HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Person> updatePerson(@RequestBody String json){
		boolean updated;
		
    	updated = controller.update(repository, json, Person.class);
    	
    	if (updated) {
        	return new ResponseEntity<>(HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
