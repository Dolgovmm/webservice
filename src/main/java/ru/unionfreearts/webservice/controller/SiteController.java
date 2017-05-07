package ru.unionfreearts.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.entity.Site;
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;

/**
 * Created by Михалыч on 26.04.2017.
 */
@Controller
@RequestMapping(value = "/site", produces = MediaType.APPLICATION_JSON_VALUE)//+ "; charset = UTF-8")
public class SiteController {

    @Autowired
    private Repository repository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> addSite(@RequestBody String json){
        Site site = null;
        ResponseEntity<Long> response;
        try {
            site = new ObjectMapper().readValue(json, Site.class);
            site.setId(repository.add(site));
            response = new ResponseEntity<Long>(site.getId(), HttpStatus.OK);
        } catch (IOException e) {
            response = new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Site> getSiteById(@PathVariable long id){
        Site site = null;
        ResponseEntity<Site> response;
        site = (Site)repository.get(id);
        if (site != null) {
            response = new ResponseEntity<Site>(site, HttpStatus.OK);
        }else{
            response = new ResponseEntity<Site>(HttpStatus.BAD_REQUEST);
            return response;
        }
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Long> removeSite(@PathVariable long id){
        ResponseEntity<Long> response;
        long removed = repository.remove(id);
        response = new ResponseEntity<Long>(removed, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Long> updateSite(@RequestBody String json){
        Site site = null;
        ResponseEntity<Long> response;
        try{
            site = new ObjectMapper().readValue(json, Site.class);
            long updated = repository.update(site);
            response = new ResponseEntity<Long>(updated, HttpStatus.OK);
        }catch (IOException ex){
            response = new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
            return response;
        }
        return response;
    }

}
