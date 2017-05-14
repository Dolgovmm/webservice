package ru.unionfreearts.webservice.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.unionfreearts.webservice.dbservice.specification.AllRanksByPersonAndTime;
import ru.unionfreearts.webservice.entity.Rank;
import ru.unionfreearts.webservice.repository.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        List<Rank> ranks = repository.query(new AllRanksByPersonAndTime(personId, siteId, new Date(startDate), new Date(finishDate)));
        List<JSONObject> jsonObjects = new ArrayList<>();
        Date nextDate = new Date(startDate);
        Date endDate = new Date(finishDate);
        do {
            JSONObject dailyRank = new JSONObject();
            for (Rank rank : ranks) {
                if (rank.getPage().getFoundDateTime().equals(nextDate) && !dailyRank.isEmpty()) {
                    dailyRank.put("rank", (Integer) dailyRank.get("rank") + rank.getRank());
                } else if (dailyRank.isEmpty() && rank.getPage().getFoundDateTime().equals(nextDate)){
                    dailyRank.put("date", rank.getPage().getFoundDateTime().getTime());
                    dailyRank.put("rank", rank.getRank());
                }
            }
            if (!dailyRank.isEmpty())
                jsonObjects.add(dailyRank);
            nextDate.setTime(nextDate.getTime() + (1000 * 60 * 60 * 24));
        }
        while (nextDate.before(endDate));

        if (ranks != null) {
            return new ResponseEntity<>(jsonObjects, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
