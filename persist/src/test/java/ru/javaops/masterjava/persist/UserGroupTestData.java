package ru.javaops.masterjava.persist;

import com.google.common.collect.ImmutableList;
import ru.javaops.masterjava.persist.dao.UserGroupDao;
import ru.javaops.masterjava.persist.model.UserGroup;

import java.util.ArrayList;
import java.util.List;

public class UserGroupTestData {
    public static UserGroup ug1;
    public static UserGroup ug2;
    public static UserGroup ug3;
    public static UserGroup ug4;

    public static List<UserGroup> ugs3;

    public static void init(){
        ug1 = new UserGroup(1,1);
        ug2 = new UserGroup(1,2);
        ug3 = new UserGroup(2,2);
        ug4 = new UserGroup(2,3);

        ugs3 = ImmutableList.of(ug1,ug2,ug3);
    }

    public static void setUp(){
        UserGroupDao dao = DBIProvider.getDao(UserGroupDao.class);

        DBIProvider.getDBI().useTransaction((conn,status)->{
            ugs3.forEach(dao::insertGeneratedId);
        });
    }
}
