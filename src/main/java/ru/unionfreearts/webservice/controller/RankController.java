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
 * Created by Михалыч on 13.05.2017.
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
        try {
            Date nextDate = new Date(startDate);
            Date endDate = new Date(finishDate);
            List<Rank> ranks = repository.query(new AllRanksByPersonAndTime(personId, siteId, nextDate, endDate));
            if (ranks != null) {
                ResponseEntity<List<JSONObject>> response = new ResponseEntity<>(
                        getDailyRanks(ranks, nextDate, endDate), HttpStatus.OK);
                return response;
            }
            ResponseEntity<List<JSONObject>> response = new ResponseEntity<>(HttpStatus.OK);
            return response;
        }catch (HibernateException ex){
            logger.error("HibernateException on add site to repository with message: " + ex.getMessage());
            ResponseEntity<List<JSONObject>> response = new ResponseEntity<>(HttpStatus.OK);
            return response;
        }
    }

    @RequestMapping(value = "/total/{siteId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<JSONObject>> getTotalStatistic(@PathVariable Long siteId) {
        List<Rank> ranks = repository.query(new AllRanksBySite(siteId));
        if (ranks != null) {
            Map<Long, Rank> rankMap = getRankMap(ranks);
            ResponseEntity<List<JSONObject>> response = new ResponseEntity<>(getJSONObjectList(rankMap), HttpStatus.OK);
            return response;
        }
        ResponseEntity<List<JSONObject>> response = new ResponseEntity<>(HttpStatus.OK);
        return response;
    }

    private List<JSONObject> getDailyRanks(List<Rank> ranks, Date nextDate, Date endDate){
        List<JSONObject> jsonObjects = new ArrayList<>();
        do {
            JSONObject dailyRank = new JSONObject();
            for (Rank rank : ranks) {
                if (rank.getPage().getFoundDateTime().equals(nextDate) && !dailyRank.isEmpty()) {
                    dailyRank.replace("rank", (Integer) dailyRank.get("rank") + rank.getRank());
                } else if (dailyRank.isEmpty() && rank.getPage().getFoundDateTime().equals(nextDate)) {
                    dailyRank.put("date", rank.getPage().getFoundDateTime().getTime());
                    dailyRank.put("rank", rank.getRank());
                }
            }
            if (!dailyRank.isEmpty())
                jsonObjects.add(dailyRank);
            nextDate.setTime(nextDate.getTime() + (1000 * 60 * 60 * 24));
        }
        while (nextDate.before(endDate));
        return jsonObjects;
    }

    private Map<Long, Rank> getRankMap(List<Rank> ranks){
        Map<Long, Rank> rankMap = new HashMap<>();
        for (Rank rank : ranks) {
            Long personId = rank.getPerson().getId();
            if (rankMap.containsKey(personId)) {
                rankMap.get(personId).setRank(rankMap.get(personId).getRank()+rank.getRank());
            } else {
                rankMap.put(rank.getPerson().getId(), rank);
            }
        }
        return rankMap;
    }

    private List<JSONObject> getJSONObjectList(Map<Long, Rank> rankMap){
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (Rank rank : rankMap.values()) {
            JSONObject totalRank = new JSONObject();
            totalRank.put("name", rank.getPerson().getName());
            totalRank.put("rank", rank.getRank());
            jsonObjects.add(totalRank);
        }
        return jsonObjects;
    }
}
