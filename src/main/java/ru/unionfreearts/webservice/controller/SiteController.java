package ru.unionfreearts.webservice.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.entity.Site;
import ru.unionfreearts.webservice.repository.FakeRepositorySiteImpl;
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;

/**
 * Created by Михалыч on 26.04.2017.
 */
@RestController
@RequestMapping(value = "/site", produces = MediaType.APPLICATION_JSON_VALUE + "; charset = UTF-8")
public class SiteController {

    //@Autowired
    private Repository repository = new FakeRepositorySiteImpl();

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> addSite(@RequestBody String json) {
        Site site = null;
        ResponseEntity<Long> response;
        try {
            site = new ObjectMapper().readValue(json, Site.class);
            repository.add(site);
            response = new ResponseEntity<Long>(site.getId(), HttpStatus.OK);
        } catch (IOException e) {
            response = new ResponseEntity<Long>(-1l, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<Site> getSiteById(@RequestBody long id){
//        Site site = null;
//        ResponseEntity<Site> response;
//        site = (Site)repository.get(id);
//        if (site != null) {
//            response = new ResponseEntity<Site>(site, HttpStatus.OK);
//        }else{
//            response = new ResponseEntity<Site>(HttpStatus.BAD_REQUEST);
//        }
//        return response;
//    }
//
//    @RequestMapping(value = "/", method = RequestMethod.DELETE)
//    @ResponseBody
//    public ResponseEntity<Boolean> removeSite(@RequestBody String json){
//        Site site = null;
//        ResponseEntity<Boolean> response;
//        try{
//            site = new ObjectMapper().readValue(json, Site.class);
//            boolean removed = repository.remove(site);
//            response = new ResponseEntity<Boolean>(removed, HttpStatus.OK);
//        }catch (IOException ex){
//            response = new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
//        }
//        return response;
//    }
//
//    @RequestMapping(value = "/", method = RequestMethod.PUT)
//    @ResponseBody
//    public ResponseEntity<Boolean> updateSite(@RequestBody String json){
//        Site site = null;
//        ResponseEntity<Boolean> response;
//        try{
//            site = new ObjectMapper().readValue(json, Site.class);
//            boolean updated = repository.update(site);
//            response = new ResponseEntity<Boolean>(updated, HttpStatus.OK);
//        }catch (IOException ex){
//            response = new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
//        }
//        return response;
//    }

}
