package ru.javaops.masterjava.persist;

import com.google.common.collect.ImmutableList;
import ru.javaops.masterjava.persist.dao.CityDao;
import ru.javaops.masterjava.persist.dao.UserDao;
import ru.javaops.masterjava.persist.dao.UserGroupDao;
import ru.javaops.masterjava.persist.model.City;

import java.util.List;

public class CityTestData {
    public static City city1;
    public static City city2;
    public static City city3;
    public static City city4;

    public static List<City> cities3;

    public static void init(){
        city1 = new City("msk","Moscow");
        city2 = new City("spb","Saint Petersburg");
        city3 = new City("vrn","Voronezh");
        city4 = new City("vkz","Vladikavkaz");
        cities3 = ImmutableList.of(city1,city2,city3);
    }

    public static void setUp(){
        CityDao dao = DBIProvider.getDao(CityDao.class);
        //UserDao userDao = DBIProvider.getDao(UserDao.class);
        //userDao.clean();
        dao.clean();
        DBIProvider.getDBI().useTransaction((conn,status)->{
            cities3.forEach(dao::insert);
        });
    }
}
