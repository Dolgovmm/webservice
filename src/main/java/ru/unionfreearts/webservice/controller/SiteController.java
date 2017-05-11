package ru.unionfreearts.webservice.controller;

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
//        Site site = null;
//        logger.debug("create empty Site instance");
//        ResponseEntity<Long> response;
//        logger.debug("create empty ResponseEntity instance");
//        try {
//            site = new ObjectMapper().readValue(json, Site.class);
//            logger.debug("get value Site instance from json");
//            site.setId(repository.add(site));
//            logger.debug("add site instance to repository: " + site.toString());
//            response = new ResponseEntity<Long>(site.getId(), HttpStatus.OK);
//            logger.debug("set responseEntity with added site id and status OK");
//        } catch (IOException ex) {
//            logger.error("IOException on read json " + json + " with messsage: " + ex.getMessage());
//            response = new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
//            logger.debug("set empty responseEntity and status BAD_REQUEST");
//        }
        long id = new AnyController<Site>().add(repository, json, Site.class);
        ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Site> getSiteById(@PathVariable long id){
        logger.debug("get Site by id method with request id: " + id);
//		Site site = null;
//        logger.debug("create empty Site instance");
//		ResponseEntity<Site> response;
//		logger.debug("create empty ResponseEntity instance");
//        site = (Site)repository.get(id);
//		logger.debug("get site instance from repository: " + site.toString());
//        if (site != null) {
//			response = new ResponseEntity<Site>(site, HttpStatus.OK);
//			logger.debug("set responseEntity with getted site and status OK");
//        }else{
//            response = new ResponseEntity<Site>(HttpStatus.BAD_REQUEST);
//			logger.debug("set empty responseEntity and status BAD_REQUEST");
//        }
        Site site = new AnyController<Site>().getById(repository, id);
        ResponseEntity<Site> response = new ResponseEntity<Site>(site, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Site>> getAllSites(){
		logger.debug("get Site list method");
        ResponseEntity<List<Site>> response = new ResponseEntity<List<Site>>(
                new AnyController<Site>().getAll(repository), HttpStatus.OK);
		logger.debug("set responseEntity with getted site list and status OK");
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Long> removeSite(@RequestBody String json){
        logger.debug("remove Site method with request json " + json);
//		Site site = null;
//        logger.debug("create empty Site instance");
//		ResponseEntity<Long> response;
//		logger.debug("create empty ResponseEntity instance");
//        try{
//            site = new ObjectMapper().readValue(json, Site.class);
//			logger.debug("get value Site instance from json");
//            long removed = repository.remove(site);
//			logger.debug("remove site from repository: " + site.toString());
//            response = new ResponseEntity<Long>(removed, HttpStatus.OK);
//			logger.debug("set responseEntity with removed and status OK");
//        }catch (IOException ex){
//            logger.error("IOException on read json " + json + " with messsage: " + ex.getMessage());
//			response = new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
//			logger.debug("set empty responseEntity and status BAD_REQUEST");
//            return response;
//        }
        long removed = new AnyController<Site>().remove(repository, json, Site.class);
        ResponseEntity<Long> response = new ResponseEntity<Long>(removed, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Long> updateSite(@RequestBody String json){
        logger.debug("update Site method with request json " + json);
//		Site site = null;
//		logger.debug("create empty Site instance");
//        ResponseEntity<Long> response;
//		logger.debug("create empty ResponseEntity instance");
//        try{
//            site = new ObjectMapper().readValue(json, Site.class);
//			logger.debug("get value Site instance from json");
//            long updated = repository.update(site);
//			logger.debug("update site on repository: " + site.toString());
//            response = new ResponseEntity<Long>(updated, HttpStatus.OK);
//			logger.debug("set responseEntity with updated site and status OK");
//        }catch (IOException ex){
//            logger.error("IOException on read json " + json + " with messsage: " + ex.getMessage());
//			response = new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
//			logger.debug("set empty responseEntity and status BAD_REQUEST");
//            return response;
//        }
        long updated = new AnyController<Site>().update(repository, json, Site.class);
        ResponseEntity<Long> response = new ResponseEntity<Long>(updated, HttpStatus.OK);
        return response;
    }
}
