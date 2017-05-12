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
import ru.unionfreearts.webservice.entity.Site;
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;
import java.util.List;

/**
 * Created by Михалыч on 26.04.2017.
 */
@Controller
@RequestMapping(value = "/site", produces = MediaType.APPLICATION_JSON_VALUE)//+ "; charset = UTF-8")
public class SiteController {
	static final Logger logger = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    @Qualifier("siteRepository")
    private Repository repository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> addSite(@RequestBody String json){
        logger.debug("add Site method with request json: " + json);
        long id;
        try {
            id = new AnyController<Site>().add(repository, json, Site.class);
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
        } catch (IOException ex) {
            id = -1l;
            logger.error("IOException on read json " + json + " with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			id = -1l;
			logger.error("HibernateException on add site to repository with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Site> getSiteById(@PathVariable long id){
        logger.debug("get Site by id method with request id: " + id);
        try {
			Site site = new AnyController<Site>().getById(repository, id);
			ResponseEntity<Site> response = new ResponseEntity<Site>(site, HttpStatus.OK);
			return response;
		} catch (HibernateException ex) {
			logger.error("HibernateException on get site by id from repository with messsage: " + ex.getMessage());
			Site emptyEntity = new Site();
			ResponseEntity<Site> response = new ResponseEntity<Site>(emptyEntity, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Site>> getAllSites(){
		logger.debug("get Site list method");
        try {
			ResponseEntity<List<Site>> response = new ResponseEntity<List<Site>>(
				new AnyController<Site>().getAll(repository), HttpStatus.OK);
			logger.debug("set responseEntity with getted site list and status OK");
			return response;
		} catch (HibernateException ex) {
			logger.error("HibernateException on get site list from repository with messsage: " + ex.getMessage());
			List<Site> list = new ArrayList<Site>();
			ResponseEntity<List<Site>> response = new ResponseEntity<List<Site>>(list, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Long> removeSite(@RequestBody String json){
        logger.debug("remove Site method with request json " + json);
        try {
			long removed = new AnyController<Site>().remove(repository, json, Site.class);
			ResponseEntity<Long> response = new ResponseEntity<Long>(removed, HttpStatus.OK);
			return response;
		} catch (IOException ex) {
            id = -1l;
            logger.error("IOException on read json " + json + " with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			id = -1l;
			logger.error("HibernateException on remove site from repository with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Long> updateSite(@RequestBody String json){
        logger.debug("update Site method with request json " + json);
		try{
			long updated = new AnyController<Site>().update(repository, json, Site.class);
			ResponseEntity<Long> response = new ResponseEntity<Long>(updated, HttpStatus.OK);
			return response;
		} catch (IOException ex) {
            id = -1l;
            logger.error("IOException on read json " + json + " with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			id = -1l;
			logger.error("HibernateException on update site on repository with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
		}
    }
}
