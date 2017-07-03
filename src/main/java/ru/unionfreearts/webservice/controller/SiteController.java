package ru.unionfreearts.webservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.dbservice.specification.AllSites;
import ru.unionfreearts.webservice.entity.Site;
import ru.unionfreearts.webservice.repository.Repository;

import java.util.List;

/**
 * SiteController working with RESTFull requests. He execute CRUD methods with site entity.
 *
 * @author M.Dolgov
 * @date 26.04.2017
 */
@RestController
@RequestMapping(value = "/site", produces = MediaType.APPLICATION_JSON_VALUE)//+ "; charset = UTF-8")
public class SiteController {
    static final Logger logger = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    @Qualifier("siteRepository")
    private Repository repository;

    @Autowired
    @Qualifier("anyControllerSite")
    private AnyController<Site> controller;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Site> addSite(@RequestBody String json) {
        Site site = null;
        site = controller.add(repository, json, Site.class);
        return new ResponseEntity<>(site, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Site> getSiteById(@PathVariable long id) {
        Site site = null;
        site = controller.getById(repository, id, Site.class);
        return new ResponseEntity<>(site, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Site>> getAllSites() {
        List<Site> list = null;
        list = controller.getAll(repository, new AllSites(), Site.class);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<Site> removeSite(@RequestBody String json) {
        boolean removed;

        removed = controller.remove(repository, json, Site.class);
        if (removed) {
        	return new ResponseEntity<>(HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Site> updateSite(@RequestBody String json) {
        boolean updated;

        updated = controller.update(repository, json, Site.class);
        if (updated) {
        	return new ResponseEntity<>(HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
}
