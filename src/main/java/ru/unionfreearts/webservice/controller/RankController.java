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
@RestController
@RequestMapping(value = "/stat", produces = MediaType.APPLICATION_JSON_VALUE)
public class RankController {
    static final Logger logger = LoggerFactory.getLogger(RankController.class);

    @Autowired
    @Qualifier("rankRepository")
    private Repository repository;

    @RequestMapping(value = "/daily", method = RequestMethod.GET)
    public ResponseEntity<List<JSONObject>> getDailyStatistic(@RequestParam(value = "personId") Long personId,
                                                              @RequestParam(value = "siteId") Long siteId,
                                                              @RequestParam(value = "startDate") Long startDate,
                                                              @RequestParam(value = "finishDate") Long finishDate){
        try {
            Date nextDate = new Date(startDate);
            Date endDate = new Date(finishDate);
            
            List<Rank> ranks = repository.query(new AllRanksByPersonAndTime(personId, siteId, nextDate, endDate));
            if (logger.isDebugEnabled()) {
	        	StringBuilder sb = new StringBuilder();
	        	sb.append("get list of ");
	        	sb.append(Rank.class);
	        	sb.append(" from repository:");
	        	sb.append(ranks.toString());
	        	logger.debug(sb.toString());
	        }

            if (ranks != null) {
            	List<JSONObject> list = null;
            	
            	list = getDailyRanks(ranks, nextDate, endDate);
            	if (logger.isDebugEnabled()) {
    	        	StringBuilder sb = new StringBuilder();
    	        	sb.append("get list of json object");
    	        	sb.append(" from ranks list:");
    	        	sb.append(list.toString());
    	        	logger.debug(sb.toString());
    	        }
            	return new ResponseEntity<>(list, HttpStatus.OK);
            }
            
            return new ResponseEntity<>(HttpStatus.OK);
            
        }catch (HibernateException ex){
        	if (logger.isErrorEnabled()) {
            	StringBuilder sb = new StringBuilder();
            	sb.append("HibernateException on get list of ranks from repository by person id {");
            	sb.append(personId);
            	sb.append("}, site id {");
            	sb.append(siteId);
            	sb.append("}, begin date {");
            	sb.append(startDate);
            	sb.append("}, end date {");
            	sb.append(finishDate);
            	sb.append("} with message: ");
            	sb.append(ex.getMessage());
            	logger.error(sb.toString());
            }   
        	return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/total/{siteId}", method = RequestMethod.GET)
    public ResponseEntity<List<JSONObject>> getTotalStatistic(@PathVariable Long siteId) {        
        try {
            List<Rank> ranks = repository.query(new AllRanksBySite(siteId));
            Map<Long, Rank> rankMap = getRankMap(ranks);
            List<JSONObject> list = getJSONObjectList(rankMap);
            if (logger.isDebugEnabled()) {
	        	StringBuilder sb = new StringBuilder();
	        	sb.append("get total statistic ");
	        	sb.append(" from repository:");
	        	sb.append(list.toString());
	        	logger.debug(sb.toString());
	        }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (HibernateException ex) {
        	if (logger.isErrorEnabled()) {
            	StringBuilder sb = new StringBuilder();
            	sb.append("HibernateException on get total statistic from repository by site id {");
            	sb.append(siteId);
            	sb.append("} with message: ");
            	sb.append(ex.getMessage());
            	logger.error(sb.toString());
            }
        	return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    private List<JSONObject> getDailyRanks(List<Rank> ranks, Date nextDate, Date endDate){
    	List<JSONObject> jsonObjects = new ArrayList<>();
    	if (ranks == null || nextDate == null || endDate == null) return jsonObjects;
        
    	Date currentDate = nextDate;
        do {
            JSONObject dailyRank = new JSONObject();
            for (Rank rank : ranks) {
                if (rank.getPage().getFoundDateTime().equals(currentDate)) {
                    if (!dailyRank.isEmpty()) {
                    	dailyRank.replace("rank", (Integer) dailyRank.get("rank") + rank.getRank());
                    } else {
                    	dailyRank.put("date", rank.getPage().getFoundDateTime().getTime());
                        dailyRank.put("rank", rank.getRank());
                    }
                }
            }
            if (!dailyRank.isEmpty()) {
                jsonObjects.add(dailyRank);
            }
            currentDate.setTime(currentDate.getTime() + (1000 * 60 * 60 * 24)); //next day
        }
        while (currentDate.before(endDate));
        
        return jsonObjects;
    }

    private Map<Long, Rank> getRankMap(List<Rank> ranks){
    	Map<Long, Rank> rankMap = new HashMap<>();
    	if (ranks == null) return rankMap;
        
    	for (Rank rank : ranks) {
            long personId = rank.getPerson().getId();
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
    	if (rankMap == null) return jsonObjects;
        
    	for (Rank rank : rankMap.values()) {
            JSONObject totalRank = new JSONObject();
            totalRank.put("name", rank.getPerson().getName());
            totalRank.put("rank", rank.getRank());
            jsonObjects.add(totalRank);
        }
        return jsonObjects;
    }
}
