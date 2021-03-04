package ru.javaops.masterjava.persist.model;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Group extends BaseEntity{
    @NonNull String name;
    @NonNull GroupType type;
    @Column("project_id")
    @NonNull int projectId;

    public Group(Integer id, String name, GroupType type, int projectId) {
        this(name,type,projectId);
        this.id = id;
    }
}
