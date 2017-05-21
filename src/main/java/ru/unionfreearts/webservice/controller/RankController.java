package ru.unionfreearts.webservice.controller;

import org.hibernate.HibernateException;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.dbservice.specification.AllRanksByPersonAndTime;
import ru.unionfreearts.webservice.dbservice.specification.AllRanksBySite;
import ru.unionfreearts.webservice.entity.Rank;
import ru.unionfreearts.webservice.repository.Repository;

import java.util.*;

/**
 * RankController working with RESTFull requests. He return daily and total statistics.
 * @author M.Dolgov
 * @date 13.05.2017
 */
@Controller
@RequestMapping(value = "/stat", produces = MediaType.APPLICATION_JSON_VALUE)
public class RankController {
    static final Logger logger = LoggerFactory.getLogger(RankController.class);

    @Autowired
    @Qualifier("rankRepository")
    private Repository repository;

    @RequestMapping(value = "/daily", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<JSONObject>> getDailyStatistic(@RequestParam(value = "personId") Long personId,
                                                              @RequestParam(value = "siteId") Long siteId,
                                                              @RequestParam(value = "startDate") Long startDate,
                                                              @RequestParam(value = "finishDate") Long finishDate){
        logger.debug("get daily statistic method");
        ResponseEntity<List<JSONObject>> response;
        try {
            Date nextDate = new Date(startDate);
            Date endDate = new Date(finishDate);
            logger.debug("set next date and end date variable");
            List<Rank> ranks = repository.query(new AllRanksByPersonAndTime(personId, siteId, nextDate, endDate));
            logger.debug("get ranks list from query");
            if (ranks != null) {
                response = new ResponseEntity<>(getDailyRanks(ranks, nextDate, endDate), HttpStatus.OK);
                logger.debug("create response list of json objects");
                return response;
            }
            response = new ResponseEntity<>(HttpStatus.OK);
            return response;
        }catch (HibernateException ex){
            logger.error("HibernateException on get list of ranks from repository with message: " + ex.getMessage());
            response = new ResponseEntity<>(HttpStatus.OK);
            return response;
        }
    }

    @RequestMapping(value = "/total/{siteId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<JSONObject>> getTotalStatistic(@PathVariable Long siteId) {
        logger.debug("get total statistic by site method");
        ResponseEntity<List<JSONObject>> response;
        try {
            List<Rank> ranks = repository.query(new AllRanksBySite(siteId));
            logger.debug("get ranks list from query");
            if (ranks != null) {
                Map<Long, Rank> rankMap = getRankMap(ranks);
                logger.debug("create rank map from ranks");
                response = new ResponseEntity<>(getJSONObjectList(rankMap), HttpStatus.OK);
                logger.debug("create response list of json objects");
                return response;
            }
        } catch (HibernateException ex) {
            logger.error("HibernateException on get total statistic repository with message: " + ex.getMessage());
            response = new ResponseEntity<>(HttpStatus.OK);
            return response;
        }
        response = new ResponseEntity<>(HttpStatus.OK);
        return response;
    }

    private List<JSONObject> getDailyRanks(List<Rank> ranks, Date nextDate, Date endDate){
        List<JSONObject> jsonObjects = new ArrayList<>();
        logger.debug("create list of json objects");
        do {
            JSONObject dailyRank = new JSONObject();
            for (Rank rank : ranks) {
                if (rank.getPage().getFoundDateTime().equals(nextDate) && !dailyRank.isEmpty()) {
                    dailyRank.replace("rank", (Integer) dailyRank.get("rank") + rank.getRank());
                    logger.debug("if rank not empty then add daily rank");
                } else if (dailyRank.isEmpty() && rank.getPage().getFoundDateTime().equals(nextDate)) {
                    dailyRank.put("date", rank.getPage().getFoundDateTime().getTime());
                    dailyRank.put("rank", rank.getRank());
                    logger.debug("if rank is empty then put daily rank");
                }
            }
            if (!dailyRank.isEmpty()) {
                jsonObjects.add(dailyRank);
                logger.debug("if daily rank not empty then add daily rank to json object list");
            }
            nextDate.setTime(nextDate.getTime() + (1000 * 60 * 60 * 24));
        }
        while (nextDate.before(endDate));
        return jsonObjects;
    }

    private Map<Long, Rank> getRankMap(List<Rank> ranks){
        Map<Long, Rank> rankMap = new HashMap<>();
        logger.debug("create rank map");
        for (Rank rank : ranks) {
            Long personId = rank.getPerson().getId();
            logger.debug("get person id");
            if (rankMap.containsKey(personId)) {
                rankMap.get(personId).setRank(rankMap.get(personId).getRank()+rank.getRank());
                logger.debug("if rank map contains person id then add rank to rank map");
            } else {
                rankMap.put(rank.getPerson().getId(), rank);
                logger.debug("if rank map not contain person id then put rank to rank map");
            }
        }
        return rankMap;
    }

    private List<JSONObject> getJSONObjectList(Map<Long, Rank> rankMap){
        List<JSONObject> jsonObjects = new ArrayList<>();
        logger.debug("create list of json objects");
        for (Rank rank : rankMap.values()) {
            JSONObject totalRank = new JSONObject();
            logger.debug("create json object total rank");
            totalRank.put("name", rank.getPerson().getName());
            totalRank.put("rank", rank.getRank());
            logger.debug("put total rank object to rank map");
            jsonObjects.add(totalRank);
            logger.debug("add total rank to rank list");
        }
        return jsonObjects;
    }
}
