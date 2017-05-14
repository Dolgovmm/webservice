package ru.unionfreearts.webservice.dbservice;

/**
 * Created by Михалыч on 14.05.2017.
 */
public class RequestFactory {
    private final static String ALLSITES = "select Site.id, Site.name from Site";
    private final static String ALLPERSON = "select Person.id, Person.name from Person";
}
