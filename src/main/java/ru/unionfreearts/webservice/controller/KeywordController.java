package ru.unionfreearts.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.entity.Keyword;
import ru.unionfreearts.webservice.repository.Repository;

import java.io.IOException;
import java.util.List;

/**
 * Created by Михалыч on 30.04.2017.
 */
@Controller
@RequestMapping(value = "/keyword", produces = MediaType.APPLICATION_JSON_VALUE)// + "; charset = UTF-8")
public class KeywordController {

    @Autowired
    @Qualifier("keywordRepository")
    Repository repository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> addKeyword(@RequestBody String json) {
        Keyword keyword = null;
        ResponseEntity<Long> response;
        try {
            keyword = new ObjectMapper().readValue(json, Keyword.class);
            keyword.setId(repository.add(keyword));
            response = new ResponseEntity<Long>(keyword.getId(), HttpStatus.OK);
        } catch (IOException e) {
            response = new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Keyword> getKeywordById(@PathVariable long id){
        Keyword keyword = null;
        ResponseEntity<Keyword> response;
        keyword = (Keyword)repository.get(id);
        if (keyword != null) {
            response = new ResponseEntity<Keyword>(keyword, HttpStatus.OK);
        }else{
            response = new ResponseEntity<Keyword>(HttpStatus.BAD_REQUEST);
            return response;
        }
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Keyword>> getKeywordList(){
        ResponseEntity<List<Keyword>> response = new ResponseEntity<List<Keyword>>(repository.getAll(), HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Long> removeKeyword(@PathVariable long id){
        ResponseEntity<Long> response;
        long removed = repository.remove(id);
        response = new ResponseEntity<Long>(removed, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Long> updateKeyword(@RequestBody String json){
        Keyword keyword = null;
        ResponseEntity<Long> response;
        try{
            keyword = new ObjectMapper().readValue(json, Keyword.class);
            long updated = repository.update(keyword);
            response = new ResponseEntity<Long>(updated, HttpStatus.OK);
        }catch (IOException ex){
            response = new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
            return response;
        }
        return response;
    }
}
