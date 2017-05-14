package ru.unionfreearts.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Михалыч on 30.04.2017.
 */
@Controller
@RequestMapping(value = "/keyword", produces = MediaType.APPLICATION_JSON_VALUE)// + "; charset = UTF-8")
public class KeywordController {
	static final Logger logger = LoggerFactory.getLogger(KeywordController.class);

    @Autowired
    @Qualifier("keywordRepository")
    Repository repository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> addKeyword(@RequestBody String json) {
        logger.debug("add Keyword method with request json: " + json);
        long id;
        try {
            id = new AnyController<Keyword>().add(repository, json, Keyword.class);
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
        } catch (IOException ex) {
            id = -1l;
            logger.error("IOException on read json " + json + " with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			id = -1l;
			logger.error("HibernateException on add Keyword to repository with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Keyword> getKeywordById(@PathVariable long id){
        logger.debug("get Keyword by id method with request id: " + id);
        try {
			Keyword keyword = new AnyController<Keyword>().getById(repository, id);
			ResponseEntity<Keyword> response = new ResponseEntity<>(keyword, HttpStatus.OK);
			return response;
		} catch (HibernateException ex) {
			logger.error("HibernateException on get Keyword by id from repository with messsage: " + ex.getMessage());
			Keyword emptyEntity = new Keyword();
			ResponseEntity<Keyword> response = new ResponseEntity<Keyword>(emptyEntity, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Keyword>> getKeywordList(){
        logger.debug("get Keyword list method");
        try {
			ResponseEntity<List<Keyword>> response = new ResponseEntity<>(
				new AnyController<Keyword>().getAll(repository, new AllKeywords()), HttpStatus.OK);
			logger.debug("set responseEntity with getted Keyword list and status OK");
			return response;
		} catch (HibernateException ex) {
			logger.error("HibernateException on get Keyword list from repository with messsage: " + ex.getMessage());
			List<Keyword> list = new ArrayList<>();
			ResponseEntity<List<Keyword>> response = new ResponseEntity<>(list, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Long> removeKeyword(@RequestBody String json){
        logger.debug("remove Keyword method with request json " + json);
        long removed;
        try {
			removed = new AnyController<Keyword>().remove(repository, json, Keyword.class);
			ResponseEntity<Long> response = new ResponseEntity<>(removed, HttpStatus.OK);
			return response;
		} catch (IOException ex) {
			removed = -1l;
            logger.error("IOException on read json " + json + " with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<>(removed, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			removed = -1l;
			logger.error("HibernateException on remove Keyword from repository with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<>(removed, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Long> updateKeyword(@RequestBody String json){
        logger.debug("update Keyword method with request json " + json);
        long updated;
		try{
			updated = new AnyController<Keyword>().update(repository, json, Keyword.class);
			ResponseEntity<Long> response = new ResponseEntity<>(updated, HttpStatus.OK);
			return response;
		} catch (IOException ex) {
			updated = -1l;
            logger.error("IOException on read json " + json + " with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<>(updated, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			updated = -1l;
			logger.error("HibernateException on update Keyword on repository with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<>(updated, HttpStatus.OK);
			return response;
		}
    }
}
