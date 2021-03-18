package ru.javaops.masterjava.persist.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.javaops.masterjava.persist.UserGroupTestData;
import ru.javaops.masterjava.persist.model.UserGroup;

import java.util.List;
import java.util.stream.Collectors;

public class UserGroupDaoTest extends AbstractDaoTest<UserGroupDao>{
    public UserGroupDaoTest() {
        super(UserGroupDao.class);
    }

    @BeforeClass
    public static void init(){
        UserGroupTestData.init();
    }

    @Before
    public void setUp(){
        UserGroupTestData.setUp();
    }

    @Test
    public void getUserGroups(){
        List<UserGroup> actual = dao.getGroupsByUserId(1);
        List<UserGroup> expected = UserGroupTestData.ugs3.stream().filter(ug -> ug.getUserId()==1).collect(Collectors.toList());
        Assert.assertEquals(expected,actual);
    }
}
