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
import ru.unionfreearts.webservice.dbservice.specification.AllPage;
import ru.unionfreearts.webservice.entity.Page;
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PageController working with RESTFull requests. He execute CRUD methods with page entity.
 * @author M.Dolgov
 * @date 03.04.2017
 */
@RestController
@RequestMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)// + "; charset = UTF-8")
public class PageController {
	static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    @Qualifier("pageRepository")
    private Repository repository;

    @Autowired
    @Qualifier("anyControllerPage")
    private AnyController<Page> controller;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Page> addPage(@RequestBody String json) {
        Page page = null;    
        
    	page = controller.add(repository, json, Page.class);
    	
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Page> getPageById(@PathVariable long id){
		Page page = null; 
		
		page = controller.getById(repository, id, Page.class);
		
		return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Page>> getPageList(){
    	List<Page> list = null;
    	
		list = controller.getAll(repository, new AllPage(), Page.class);

		return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<Page> removePage(@RequestBody String json){
    	boolean removed;
    	
		removed = controller.remove(repository, json, Page.class);
		
		if (removed) {
        	return new ResponseEntity<>(HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Page> updatePage(@RequestBody String json){
		boolean updated;
		
		updated = controller.update(repository, json, Page.class);
		
		if (updated) {
        	return new ResponseEntity<>(HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
