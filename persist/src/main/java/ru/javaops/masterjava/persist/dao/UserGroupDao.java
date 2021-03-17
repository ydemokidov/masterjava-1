package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.model.UserGroup;

import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
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

    @SqlUpdate("INSERT INTO user_groups (user_id,group_id) VALUES (:userId,:groupId)")
    @GetGeneratedKeys
    public abstract UserGroup insertGeneratedId(@BindBean UserGroup userGroup);
}
