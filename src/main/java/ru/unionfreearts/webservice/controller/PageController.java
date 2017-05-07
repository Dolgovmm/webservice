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
        Page page = null;
        ResponseEntity<Long> response;
        try {
            page = new ObjectMapper().readValue(json, Page.class);
            page.setId(repository.add(page));
            response = new ResponseEntity<Long>(page.getId(), HttpStatus.OK);
        } catch (IOException e) {
            response = new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page> getPageById(@PathVariable long id){
        Page page = null;
        ResponseEntity<Page> response;
        page = (Page)repository.get(id);
        if (page != null) {
            response = new ResponseEntity<Page>(page, HttpStatus.OK);
        }else{
            response = new ResponseEntity<Page>(HttpStatus.BAD_REQUEST);
            return response;
        }
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Page>> getPageList(){
        ResponseEntity<List<Page>> response = new ResponseEntity<List<Page>>(repository.getAll(), HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Long> removePage(@PathVariable long id){
        ResponseEntity<Long> response;
        long removed = repository.remove(id);
        response = new ResponseEntity<Long>(removed, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Long> updatePage(@RequestBody String json){
        Page page = null;
        ResponseEntity<Long> response;
        try{
            page = new ObjectMapper().readValue(json, Page.class);
            long updated = repository.update(page);
            response = new ResponseEntity<Long>(updated, HttpStatus.OK);
        }catch (IOException ex){
            response = new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
            return response;
        }
        return response;
    }
}
