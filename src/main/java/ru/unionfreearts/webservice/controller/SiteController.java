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
import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Long> addSite(@RequestBody String json) {
        System.out.println(json);
        Site site = null;
        ResponseEntity<Long> response;
        try {
            site = new ObjectMapper().readValue(json, Site.class);
            System.out.println("site name = " + site.getName());
            site.setId(repository.add(site));
            System.out.println("site id = " + site.getId());
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
        //site = new Site(1l, "lenta.ru");
        if (site != null) {
            response = new ResponseEntity<Site>(site, HttpStatus.OK);
        }else{
            response = new ResponseEntity<Site>(HttpStatus.BAD_REQUEST);
            return response;
        }
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Boolean> removeSite(@RequestBody String json){
        Site site = null;
        ResponseEntity<Boolean> response;
        try{
            site = new ObjectMapper().readValue(json, Site.class);
            boolean removed = repository.remove(site);
            response = new ResponseEntity<Boolean>(removed, HttpStatus.OK);
        }catch (IOException ex){
            response = new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
            return response;
        }
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Boolean> updateSite(@RequestBody String json){
        Site site = null;
        ResponseEntity<Boolean> response;
        try{
            site = new ObjectMapper().readValue(json, Site.class);
            boolean updated = repository.update(site);
            response = new ResponseEntity<Boolean>(updated, HttpStatus.OK);
        }catch (IOException ex){
            response = new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
            return response;
        }
        return response;
    }

}
