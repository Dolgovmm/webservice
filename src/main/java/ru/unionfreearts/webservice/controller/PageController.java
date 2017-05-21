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
@Controller
@RequestMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)// + "; charset = UTF-8")
public class PageController {
	static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    @Qualifier("pageRepository")
    private Repository repository;

    private AnyController<Page> controller = new AnyController<>();

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> addPage(@RequestBody String json) {
        logger.debug("add Page method with request json: " + json);
        long id;
		ResponseEntity<Long> response;
        try {
            id = controller.add(repository, json, Page.class);
			response = new ResponseEntity<>(id, HttpStatus.OK);
			return response;
        } catch (IOException ex) {
            id = -1l;
            logger.error("IOException on read json " + json + " with message: " + ex.getMessage());
			response = new ResponseEntity<>(id, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			id = -1l;
			logger.error("HibernateException on add Page to repository with message: " + ex.getMessage());
			response = new ResponseEntity<>(id, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page> getPageById(@PathVariable long id){
        logger.debug("get Page by id method with request id: " + id);
		ResponseEntity<Page> response;
        try {
			Page page = controller.getById(repository, id);
			response = new ResponseEntity<>(page, HttpStatus.OK);
			return response;
		} catch (HibernateException ex) {
			logger.error("HibernateException on get Page by id from repository with message: " + ex.getMessage());
			Page emptyEntity = new Page();
			response = new ResponseEntity<>(emptyEntity, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Page>> getPageList(){
        logger.debug("get Page list method");
		ResponseEntity<List<Page>> response;
        try {
			response = new ResponseEntity<>(controller.getAll(repository, new AllPage()), HttpStatus.OK);
			logger.debug("set responseEntity with getted Page list and status OK");
			return response;
		} catch (HibernateException ex) {
			logger.error("HibernateException on get Page list from repository with message: " + ex.getMessage());
			List<Page> list = new ArrayList<>();
			response = new ResponseEntity<>(list, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Long> removePage(@RequestBody String json){
        logger.debug("remove Page method with request json " + json);
        long removed;
		ResponseEntity<Long> response;
        try {
			removed = controller.remove(repository, json, Page.class);
			response = new ResponseEntity<>(removed, HttpStatus.OK);
			return response;
		} catch (IOException ex) {
			removed = -1l;
            logger.error("IOException on read json " + json + " with message: " + ex.getMessage());
			response = new ResponseEntity<>(removed, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			removed = -1l;
			logger.error("HibernateException on remove Page from repository with message: " + ex.getMessage());
			response = new ResponseEntity<>(removed, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Long> updatePage(@RequestBody String json){
        logger.debug("update Page method with request json " + json);
        long updated;
		ResponseEntity<Long> response;
        try{
			updated = controller.update(repository, json, Page.class);
			response = new ResponseEntity<>(updated, HttpStatus.OK);
			return response;
		} catch (IOException ex) {
			updated = -1l;
            logger.error("IOException on read json " + json + " with message: " + ex.getMessage());
			response = new ResponseEntity<>(updated, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			updated = -1l;
			logger.error("HibernateException on update Page on repository with message: " + ex.getMessage());
			response = new ResponseEntity<>(updated, HttpStatus.OK);
			return response;
		}
    }
}
