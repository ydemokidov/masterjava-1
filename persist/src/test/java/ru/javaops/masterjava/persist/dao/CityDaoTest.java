package ru.javaops.masterjava.persist.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.javaops.masterjava.persist.CityTestData;
import ru.javaops.masterjava.persist.model.City;

import java.util.List;

import static ru.javaops.masterjava.persist.CityTestData.cities3;

public class CityDaoTest extends AbstractDaoTest<CityDao>{
    public CityDaoTest(){
        super(CityDao.class);
    }

    @BeforeClass
    public static void init() throws Exception{
        CityTestData.init();
    }

    @Before
    public void setUp(){
        CityTestData.setUp();
    }

    @Test
    public void getCities(){
        List<City> actual = dao.getCitiesList();
        Assert.assertEquals(cities3,actual);
    }
}
