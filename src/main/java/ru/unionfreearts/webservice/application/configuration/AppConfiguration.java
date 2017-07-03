package ru.unionfreearts.webservice.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.unionfreearts.webservice.controller.AnyController;
import ru.unionfreearts.webservice.dbservice.DbService;
import ru.unionfreearts.webservice.dbservice.DbServiceImpl;
import ru.unionfreearts.webservice.entity.*;
import ru.unionfreearts.webservice.repository.*;

/**
 * @author M.Dolgov
 * @date 02.07.2017.
 */
@Configuration
@ComponentScan("ru.unionfreearts.webservice")
public class AppConfiguration {

    @Bean(name = "siteRepository")
    public Repository siteRepositoryImpl() {
        return new SiteRepositoryImpl();
    }

    @Bean(name = "dbServiceSite")
    public DbService<Site> dbServiceSite() {
        return new DbServiceImpl<>(Site.class);
    }

    @Bean(name = "anyControllerSite")
    public AnyController<Site> getAnyControllerSite() {
        return new AnyController<>();
    }

    @Bean(name = "personRepository")
    public Repository personRepositoryImpl() {
        return new PersonRepositoryImpl();
    }

    @Bean(name = "dbServicePerson")
    public DbService<Person> dbServicePerson() {
        return new DbServiceImpl<>(Person.class);
    }

    @Bean(name = "anyControllerPerson")
    public AnyController<Person> getAnyControllerPerson() {
        return new AnyController<>();
    }

    @Bean(name = "pageRepository")
    public Repository pagerRepositoryImpl(){
        return new PageRepositoryImpl();
    }

    @Bean(name = "dbServicePage")
    public DbService<Page> dbServicePage() {
        return new DbServiceImpl<>(Page.class);
    }

    @Bean(name = "anyControllerPage")
    public AnyController<Page> getAnyControllerPage() {
        return new AnyController<>();
    }

    @Bean(name = "keywordRepository")
    public Repository keywordRepositoryImpl() {
        return new KeywordRepositoryImpl();
    }

    @Bean(name = "dbServiceKeyword")
    public DbService<Keyword> dbServiceKeyword() {
        return new DbServiceImpl<>(Keyword.class);
    }

    @Bean(name = "anyControllerKeyword")
    public AnyController<Keyword> getAnyControllerKeyword() {
        return new AnyController<>();
    }

    @Bean(name = "rankRepository")
    public Repository rankRepositoryImpl() {
        return new RankRepositoryImpl();
    }

    @Bean(name = "dbServiceRank")
    public DbService<Rank> dbServiceRank() {
        return new DbServiceImpl<>(Rank.class);
    }


}
