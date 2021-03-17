package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.model.Group;

import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class GroupDao implements AbstractDao{
    @Override
    @SqlUpdate("TRUNCATE TABLE groups")
    public abstract void clean();

    @SqlQuery("SELECT * FROM groups ORDER BY name")
    public abstract List<Group> getGroupsList();

    @SqlQuery("SELECT * FROM groups WHERE id = :id")
    public abstract Group getGroup(@Bind int id);

    @SqlUpdate("INSERT INTO groups (name,type,project_id) VALUES (:name,:type,:projectId)")
    @GetGeneratedKeys
    public abstract void insertGeneratedId(@BindBean Group group);
}
