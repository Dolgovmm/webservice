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
import ru.unionfreearts.webservice.dbservice.specification.AllSites;
import ru.unionfreearts.webservice.entity.Site;
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SiteController working with RESTFull requests. He execute CRUD methods with site entity.
 *
 * @author M.Dolgov
 * @date 26.04.2017
 */
@Controller
@RequestMapping(value = "/site", produces = MediaType.APPLICATION_JSON_VALUE)//+ "; charset = UTF-8")
public class SiteController {
    static final Logger logger = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    @Qualifier("siteRepository")
    private Repository repository;

    private AnyController<Site> controller = new AnyController<>();

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> addSite(@RequestBody String json) {
        logger.debug("add Site method with request json: " + json);
        long id;
        ResponseEntity<Long> response;
        try {
            id = controller.add(repository, json, Site.class);
            response = new ResponseEntity<>(id, HttpStatus.OK);
            return response;
        } catch (IOException ex) {
            id = -1l;
            logger.error("IOException on read json " + json + " with message: " + ex.getMessage());
            response = new ResponseEntity<>(id, HttpStatus.OK);
            return response;
        } catch (HibernateException ex) {
            id = -1l;
            logger.error("HibernateException on add site to repository with message: " + ex.getMessage());
            response = new ResponseEntity<>(id, HttpStatus.OK);
            return response;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Site> getSiteById(@PathVariable long id) {
        logger.debug("get Site by id method with request id: " + id);
        ResponseEntity<Site> response;
        try {
            Site site = controller.getById(repository, id);
            response = new ResponseEntity<>(site, HttpStatus.OK);
            return response;
        } catch (HibernateException ex) {
            logger.error("HibernateException on get site by id from repository with messsage: " + ex.getMessage());
            Site emptyEntity = new Site();
            response = new ResponseEntity<>(emptyEntity, HttpStatus.OK);
            return response;
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Site>> getAllSites() {
        logger.debug("get Site list method");
        ResponseEntity<List<Site>> response;
        try {
            response = new ResponseEntity<>(
                    controller.getAll(repository, new AllSites()), HttpStatus.OK);
            logger.debug("set responseEntity with getted site list and status OK");
            return response;
        } catch (HibernateException ex) {
            logger.error("HibernateException on get site list from repository with message: " + ex.getMessage());
            List<Site> list = new ArrayList<>();
            response = new ResponseEntity<>(list, HttpStatus.OK);
            return response;
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Long> removeSite(@RequestBody String json) {
        logger.debug("remove Site method with request json " + json);
        long removed;
        ResponseEntity<Long> response;
        try {
            removed = controller.remove(repository, json, Site.class);
            response = new ResponseEntity<>(removed, HttpStatus.OK);
            return response;
        } catch (IOException ex) {
            removed = -1l;
            logger.error("IOException on read json " + json + " with message: " + ex.getMessage());
            response = new ResponseEntity<>(removed, HttpStatus.OK);
            return response;
        } catch (HibernateException ex) {
            removed = -1l;
            logger.error("HibernateException on remove site from repository with message: " + ex.getMessage());
            response = new ResponseEntity<>(removed, HttpStatus.OK);
            return response;
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Long> updateSite(@RequestBody String json) {
        logger.debug("update Site method with request json " + json);
        long updated;
        ResponseEntity<Long> response;
        try {
            updated = controller.update(repository, json, Site.class);
            response = new ResponseEntity<>(updated, HttpStatus.OK);
            return response;
        } catch (IOException ex) {
            updated = -1l;
            logger.error("IOException on read json " + json + " with message: " + ex.getMessage());
            response = new ResponseEntity<>(updated, HttpStatus.OK);
            return response;
        } catch (HibernateException ex) {
            updated = -1l;
            logger.error("HibernateException on update site on repository with message: " + ex.getMessage());
            response = new ResponseEntity<>(updated, HttpStatus.OK);
            return response;
        }
    }
}
