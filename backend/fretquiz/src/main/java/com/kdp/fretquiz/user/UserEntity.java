package com.kdp.fretquiz.user;

import com.kdp.fretquiz.user.ImmutableUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;
import java.util.Optional;

@Table("users")
public class UserEntity
{
    @Id private final Long id;
    private final String sessionId;
    private final String name;
    private final Long gameId;

    UserEntity(Long id, String sessionId, String name, Long gameId)
    {
        this.id = id;
        this.sessionId = sessionId;
        this.name = name;
        this.gameId = gameId;
    }

    public static UserEntity from(User user)
    {
        return new UserEntity(
                user.id(),
                user.sessionId(),
                user.name(),
                user.gameId().orElse(null));
    }

    public static User toUser(UserEntity entity)
    {
        return ImmutableUser.builder()
                .id(entity.id)
                .sessionId(entity.sessionId)
                .name(entity.name)
                .gameId(Optional.ofNullable(entity.gameId))
                .build();
    }

    public Long getId()
    {
        return id;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public String getName()
    {
        return name;
    }

    public Long getGameId()
    {
        return gameId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(sessionId, that.sessionId) && Objects.equals(name, that.name) && Objects.equals(gameId, that.gameId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, sessionId, name, gameId);
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", sessionId='" + sessionId + '\'' +
                ", name='" + name + '\'' +
                ", gameId=" + gameId +
                '}';
    }
}
