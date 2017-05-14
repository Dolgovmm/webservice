package ru.unionfreearts.webservice.dbservice;

/**
 * Created by Михалыч on 14.05.2017.
 */
public class RequestFactory {
    private final static String ALLSITES = "select Site.id, Site.name from Site";
    private final static String ALLPERSON = "select Person.id, Person.name from Person";
    private final static String ALLKEYWORDS = "select Keyword.id, Keyword.name from Keyword";
    private final static String ALLRANKSBYSITE = "select Rank.rank from Rank where ";
    private final static String ALLRANKSBYPERSON = "select Rank.rank from Rank where Person.id = :nameParam";


}
