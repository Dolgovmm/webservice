package ru.unionfreearts.webservice.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.unionfreearts.webservice.controller.AnyController;
import ru.unionfreearts.webservice.entity.Site;

import java.io.IOException;

/**
 *
 * @author M.Dolgov
 * create date 15.05.2017
 */
public class TestSiteRepositoryImpl {

    private Repository repository;

    @Before
    public void init(){
        repository = new FakeRepositorySiteImpl();
        repository.add(new Site(0, "lenta.ru"));
        repository.add(new Site(1, "yandex.ru"));
        repository.add(new Site(2, "google.ru"));
    }

    @Test
    public void testAnyControllerAddSiteMethod(){
        long id = -2;
        try {
            id = new AnyController<Site>().add(repository, "{\n \"id\": -1,\n\"name\": \"vk.ru\"\n}", Site.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(3, id);
    }

    @Test
    public void testAnyControllerGetSiteMethod(){
        Site site = new Site(0, "lenta.ru");
        Assert.assertEquals(site, new AnyController<Site>().getById(repository,0));
    }

    @Test
    public void testAnyControllerRemoveSiteMethod(){
        long removed = -2;
        try {
            removed = new AnyController<Site>().remove(repository,
                    "{\n \"id\": 1,\n\"name\": \"yandex.ru\"\n}",
                    Site.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, removed);
    }

    @Test
    public void testAnyControllerUdateSiteMethod(){
        long updated = -2;
        try {
            updated = new AnyController<Site>().update(repository,
                    "{\n \"id\": 2,\n\"name\": \"google.com\"\n}", Site.class);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        Assert.assertEquals(1, updated);


    }
}
