package ru.javaops.masterjava.persist.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.model.City;

import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class CityDao implements AbstractDao{

    @SqlQuery("SELECT nextval('city_seq')")
    abstract int getNextVal();

    public City insert(City city){
        if(city.isNew()){
            int id = insertGeneratedId(city);
            city.setId(id);
        }else{
            insertWitId(city);
        }
        return city;
    }

    @SqlUpdate("INSERT INTO cities (short_name,name) VALUES (:shortName, :name)")
    @GetGeneratedKeys
    abstract int insertGeneratedId(@BindBean City city);

    @SqlUpdate("INSERT INTO cities (id, short_name,name) VALUES (:id, :shortName, :name)")
    abstract void insertWitId(@BindBean City city);

    @SqlUpdate("TRUNCATE cities CASCADE")
    @Override
    public abstract void clean();

    @SqlQuery("SELECT * from cities ORDER BY name")
    public abstract List<City> getCitiesList();

    @SqlQuery("SELECT * FROM cities WHERE id= :id")
    public abstract City getCityById(int id);

}
