package ru.javaops.masterjava.persist.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import ru.javaops.masterjava.persist.model.UserGroup;

import java.util.List;

public abstract class UserGroupDao implements AbstractDao{
    @Override
    @SqlUpdate("TRUNCATE user_groups cascade")
    public abstract void clean();

    @SqlQuery("SELECT NEXTVAL(user_group_seq)")
    abstract int getNextVal();

    @SqlQuery("SELECT * FROM user_groups WHERE user_id = :userId")
    public abstract List<UserGroup> getGroupsByUserId(@Bind int userId);

    @SqlQuery("SELECT * FROM user_groups WHERE group_id = :groupId")
    public abstract List<UserGroup> getUsersByGroupId(@Bind int groupId);
}
