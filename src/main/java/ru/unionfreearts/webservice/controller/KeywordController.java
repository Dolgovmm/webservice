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
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * KeywordController working with RESTFull requests. He execute CRUD methods with keyword entity.
 * @author M.Dolgov
 * @date 03.04.2017
 */
@Controller
@RequestMapping(value = "/keyword", produces = MediaType.APPLICATION_JSON_VALUE)// + "; charset = UTF-8")
public class KeywordController {
	static final Logger logger = LoggerFactory.getLogger(KeywordController.class);

    @Autowired
    @Qualifier("keywordRepository")
    private Repository repository;

    private AnyController<Keyword> controller = new AnyController<>();

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> addKeyword(@RequestBody String json) {
        logger.debug("add Keyword method with request json: " + json);
        long id;
		ResponseEntity<Long> response;
        try {
            id = controller.add(repository, json, Keyword.class);
			response = new ResponseEntity<>(id, HttpStatus.OK);
			return response;
        } catch (IOException ex) {
            id = -1l;
            logger.error("IOException on read json " + json + " with message: " + ex.getMessage());
			response = new ResponseEntity<>(id, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			id = -1l;
			logger.error("HibernateException on add Keyword to repository with message: " + ex.getMessage());
			response = new ResponseEntity<>(id, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Keyword> getKeywordById(@PathVariable long id){
        logger.debug("get Keyword by id method with request id: " + id);
		ResponseEntity<Keyword> response;
        try {
			Keyword keyword = controller.getById(repository, id);
			response = new ResponseEntity<>(keyword, HttpStatus.OK);
			return response;
		} catch (HibernateException ex) {
			logger.error("HibernateException on get Keyword by id from repository with message: " + ex.getMessage());
			Keyword emptyEntity = new Keyword();
			response = new ResponseEntity<>(emptyEntity, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Keyword>> getKeywordList(){
        logger.debug("get Keyword list method");
		ResponseEntity<List<Keyword>> response;
        try {
			response = new ResponseEntity<>(controller.getAll(repository, new AllKeywords()), HttpStatus.OK);
			logger.debug("set responseEntity with getted Keyword list and status OK");
			return response;
		} catch (HibernateException ex) {
			logger.error("HibernateException on get Keyword list from repository with messsage: " + ex.getMessage());
			List<Keyword> list = new ArrayList<>();
			response = new ResponseEntity<>(list, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Long> removeKeyword(@RequestBody String json){
        logger.debug("remove Keyword method with request json " + json);
        long removed;
		ResponseEntity<Long> response;
        try {
			removed = controller.remove(repository, json, Keyword.class);
			response = new ResponseEntity<>(removed, HttpStatus.OK);
			return response;
		} catch (IOException ex) {
			removed = -1l;
            logger.error("IOException on read json " + json + " with message: " + ex.getMessage());
			response = new ResponseEntity<>(removed, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			removed = -1l;
			logger.error("HibernateException on remove Keyword from repository with message: " + ex.getMessage());
			response = new ResponseEntity<>(removed, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Long> updateKeyword(@RequestBody String json){
        logger.debug("update Keyword method with request json " + json);
        long updated;
		ResponseEntity<Long> response;
        try{
			updated = controller.update(repository, json, Keyword.class);
			response = new ResponseEntity<>(updated, HttpStatus.OK);
			return response;
		} catch (IOException ex) {
			updated = -1l;
            logger.error("IOException on read json " + json + " with message: " + ex.getMessage());
			response = new ResponseEntity<>(updated, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			updated = -1l;
			logger.error("HibernateException on update Keyword on repository with message: " + ex.getMessage());
			response = new ResponseEntity<>(updated, HttpStatus.OK);
			return response;
		}
    }
}
