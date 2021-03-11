package ru.javaops.masterjava.persist.model;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Project extends BaseEntity{
    @NonNull String name;

    public Project(Integer id, String name) {
        this(name);
        this.setId(id);
    }
}
