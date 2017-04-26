package ru.unionfreearts.webservice.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.unionfreearts.webservice.entity.Site;
import ru.unionfreearts.webservice.repository.FakeRepositorySiteImpl;
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;

/**
 * Created by Михалыч on 26.04.2017.
 */
@Controller
@RequestMapping(value = "/site", produces = MediaType.APPLICATION_JSON_VALUE + "; charset = UTF-8")
public class SiteController {

    @Autowired
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
}
