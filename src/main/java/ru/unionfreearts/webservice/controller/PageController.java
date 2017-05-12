package ru.unionfreearts.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.entity.Page;
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;
import java.util.List;

/**
 * Created by Михалыч on 30.04.2017.
 */
@Controller
@RequestMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)// + "; charset = UTF-8")
public class PageController {

    @Autowired
    @Qualifier("pageRepository")
    Repository repository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> addPage(@RequestBody String json) {
        logger.debug("add Page method with request json: " + json);
        long id;
        try {
            id = new AnyController<Page>().add(repository, json, Page.class);
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
        } catch (IOException ex) {
            id = -1l;
            logger.error("IOException on read json " + json + " with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			id = -1l;
			logger.error("HibernateException on add Page to repository with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page> getPageById(@PathVariable long id){
        logger.debug("get Page by id method with request id: " + id);
        try {
			Page page = new AnyController<Page>().getById(repository, id);
			ResponseEntity<Page> response = new ResponseEntity<>(page, HttpStatus.OK);
			return response;
		} catch (HibernateException ex) {
			logger.error("HibernateException on get Page by id from repository with messsage: " + ex.getMessage());
			Page emptyEntity = new Page();
			ResponseEntity<Page> response = new ResponseEntity<Page>(emptyEntity, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Page>> getPageList(){
        logger.debug("get Page list method");
        try {
			ResponseEntity<List<Page>> response = new ResponseEntity<>(
				new AnyController<Page>().getAll(repository), HttpStatus.OK);
			logger.debug("set responseEntity with getted Page list and status OK");
			return response;
		} catch (HibernateException ex) {
			logger.error("HibernateException on get Page list from repository with messsage: " + ex.getMessage());
			List<Page> list = new ArrayList<Page>();
			ResponseEntity<List<Page>> response = new ResponseEntity<>(list, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Long> removePage(@RequestBody String json){
        logger.debug("remove Page method with request json " + json);
        try {
			long removed = new AnyController<Page>().remove(repository, json, Page.class);
			ResponseEntity<Long> response = new ResponseEntity<Long>(removed, HttpStatus.OK);
			return response;
		} catch (IOException ex) {
            id = -1l;
            logger.error("IOException on read json " + json + " with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			id = -1l;
			logger.error("HibernateException on remove Page from repository with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
		}
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Long> updatePage(@RequestBody String json){
        logger.debug("update Page method with request json " + json);
		try{
			long updated = new AnyController<Page>().update(repository, json, Page.class);
			ResponseEntity<Long> response = new ResponseEntity<Long>(updated, HttpStatus.OK);
			return response;
		} catch (IOException ex) {
            id = -1l;
            logger.error("IOException on read json " + json + " with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
        } catch (HibernateException ex) {
			id = -1l;
			logger.error("HibernateException on update Page on repository with messsage: " + ex.getMessage());
			ResponseEntity<Long> response = new ResponseEntity<Long>(id, HttpStatus.OK);
			return response;
		}
    }
}
