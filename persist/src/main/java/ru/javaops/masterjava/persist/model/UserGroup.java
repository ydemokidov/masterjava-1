package ru.javaops.masterjava.persist.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserGroup extends BaseEntity{
    @NonNull int UserId;
    @NonNull int GroupId;

    public UserGroup(Integer id, int userId, int groupId) {
        this(userId,groupId);
        this.setId(id);
    }
}
