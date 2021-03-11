package ru.javaops.masterjava.persist.dao;

import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public abstract class UserGroupDao implements AbstractDao{
    @Override
    @SqlUpdate("TRUNCATE user_groups")
    public abstract void clean();
}
