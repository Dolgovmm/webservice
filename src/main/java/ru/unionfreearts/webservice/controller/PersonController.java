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
    public ResponseEntity<Long> addPerson(@RequestBody String json) {
        logger.debug("add Person method with request json: " + json);
        long id;
		ResponseEntity<Long> response;
        try {
            id = controller.add(repository, json, Person.class);
			response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
        } catch (IOException ex) {
            id = -1l;
            logger.error("IOException on read json " + json + " with message: " + ex.getMessage());
			response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			id = -1l;
			logger.error("HibernateException on add person to repository with message: " + ex.getMessage());
			response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Person> getPersonById(@PathVariable long id){
        logger.debug("get Person by id method with request id: " + id);
		ResponseEntity<Person> response;
        try {
			Person person = controller.getById(repository, id);
			response = new ResponseEntity<>(person, HttpStatus.OK);
			return response;
		} catch (HibernateException ex) {
			logger.error("HibernateException on get person by id from repository with message: " + ex.getMessage());
			Person emptyEntity = new Person();
			response = new ResponseEntity<>(emptyEntity, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Person>> getPersonList(){
        logger.debug("get Person list method");
		ResponseEntity<List<Person>> response;
        try {
			response = new ResponseEntity<>(controller.getAll(repository, new AllPerson()), HttpStatus.OK);
			logger.debug("set responseEntity with getted person list and status OK");
			return response;
		} catch (HibernateException ex) {
			logger.error("HibernateException on get person list from repository with message: " + ex.getMessage());
			List<Person> list = new ArrayList<>();
			response = new ResponseEntity<>(list, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Long> removePerson(@RequestBody String json){
        logger.debug("remove Person method with request json " + json);
        long removed;
		ResponseEntity<Long> response;
        try {
			removed = controller.remove(repository, json, Person.class);
			response = new ResponseEntity<>(removed, HttpStatus.OK);
			return response;
		} catch (IOException ex) {
			removed = -1l;
            logger.error("IOException on read json " + json + " with message: " + ex.getMessage());
			response = new ResponseEntity<>(removed, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			removed = -1l;
			logger.error("HibernateException on remove Person from repository with message: " + ex.getMessage());
			response = new ResponseEntity<>(removed, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Long> updatePerson(@RequestBody String json){
        logger.debug("update Person method with request json " + json);
		long updated;
		ResponseEntity<Long> response;
		try{
			updated = controller.update(repository, json, Person.class);
			response = new ResponseEntity<>(updated, HttpStatus.OK);
			return response;
		} catch (IOException ex) {
			updated = -1l;
            logger.error("IOException on read json " + json + " with message: " + ex.getMessage());
			response = new ResponseEntity<>(updated, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			updated = -1l;
			logger.error("HibernateException on update Person on repository with message: " + ex.getMessage());
			response = new ResponseEntity<>(updated, HttpStatus.OK);
			return response;
		}

    }
}
