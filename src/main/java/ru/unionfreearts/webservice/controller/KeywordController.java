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
import ru.unionfreearts.webservice.dbservice.specification.AllKeywords;
import ru.unionfreearts.webservice.entity.Keyword;
import ru.unionfreearts.webservice.entity.Page;
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * KeywordController working with RESTFull requests. He execute CRUD methods with keyword entity.
 * @author M.Dolgov
 * @date 03.04.2017
 */
@RestController
@RequestMapping(value = "/keyword", produces = MediaType.APPLICATION_JSON_VALUE)// + "; charset = UTF-8")
public class KeywordController {
	static final Logger logger = LoggerFactory.getLogger(KeywordController.class);

    @Autowired
    @Qualifier("keywordRepository")
    private Repository repository;

    @Autowired
    @Qualifier("anyControllerKeyword")
    private AnyController<Keyword> controller;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Keyword> addKeyword(@RequestBody String json) {
        Keyword keyword = null;   
        
        keyword = controller.add(repository, json, Keyword.class);
        
		return new ResponseEntity<>(keyword, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Keyword> getKeywordById(@PathVariable long id){
		Keyword keyword = null;
		
		keyword = controller.getById(repository, id, Keyword.class);
		
		return new ResponseEntity<>(keyword, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Keyword>> getKeywordList(){
		List<Keyword> list = null;
		
    	list = controller.getAll(repository, new AllKeywords(), Keyword.class);
    	
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<Keyword> removeKeyword(@RequestBody String json){
    	boolean removed;
    	
		removed = controller.remove(repository, json, Keyword.class);
		
		if (removed) {
        	return new ResponseEntity<>(HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Keyword> updateKeyword(@RequestBody String json){
    	boolean updated;
		
		updated = controller.update(repository, json, Keyword.class);
		
		if (updated) {
        	return new ResponseEntity<>(HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
