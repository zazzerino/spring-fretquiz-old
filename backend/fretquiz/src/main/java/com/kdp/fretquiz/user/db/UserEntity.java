package com.kdp.fretquiz.user.db;

import com.kdp.fretquiz.user.ImmutableUser;
import com.kdp.fretquiz.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("user")
public class UserEntity
{
    @Id
    public final Long id;
    public final String sessionId;
    public final String name;

    public UserEntity(Long id, String sessionId, String name)
    {
        this.id = id;
        this.sessionId = sessionId;
        this.name = name;
    }

    public static UserEntity create(String sessionId)
    {
        return new UserEntity(null, sessionId, User.DEFAULT_NAME);
    }

    public static UserEntity from(User user)
    {
        return new UserEntity(
                user.id(),
                user.sessionId(),
                user.name());
    }

    public User toUser()
    {
        return ImmutableUser.builder()
                .id(id)
                .sessionId(sessionId)
                .name(name)
                .build();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(sessionId, that.sessionId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, sessionId, name);
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", sessionId='" + sessionId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
