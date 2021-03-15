package ru.javaops.masterjava.persist.model;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
//@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserGroup extends BaseEntity{
    @NotNull int UserId;
    @NotNull int GroupId;

    public UserGroup(int userId, int groupId) {
        UserId = userId;
        GroupId = groupId;
    }

    public UserGroup(Integer id, int userId, int groupId) {
        this(userId,groupId);
        this.setId(id);
    }
}
