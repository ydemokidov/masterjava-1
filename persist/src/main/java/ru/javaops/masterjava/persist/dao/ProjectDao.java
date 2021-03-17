package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.model.Project;

import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class ProjectDao implements AbstractDao{
    @Override
    @SqlUpdate("TRUNCATE TABLE projects")
    public abstract void clean();

    @SqlQuery("SELECT * FROM projects ORDER BY name")
    public abstract List<Project> getProjectsList();

    @SqlQuery("SELECT * FROM projects WHERE id = :id")
    public abstract Project getProject(@Bind int id);

    @SqlUpdate("INSERT INTO projects (name) VALUES (:name)")
    @GetGeneratedKeys
    public abstract Project insertGeneratedId(@BindBean Project project);
}
