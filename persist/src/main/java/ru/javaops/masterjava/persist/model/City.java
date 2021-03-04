package ru.javaops.masterjava.persist.model;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class City extends BaseEntity{
    @Column("short_name")
    @NonNull String shortName;
    @NonNull String name;

    public City(Integer id, String shortName, String name) {
        this(shortName,name);
        this.id = id;
    }
}
