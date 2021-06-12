package com.kdp.fretquiz.user;

import com.kdp.fretquiz.user.ImmutableUser;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

import java.util.Optional;

@Value.Immutable
public abstract class User
{
    @Nullable public abstract Long id();
    public abstract String sessionId();
    public abstract String name();
    public abstract Optional<Long> gameId();

    public static final String DEFAULT_NAME = "anon";

    public static User create(String sessionId)
    {
        return ImmutableUser.builder()
                .sessionId(sessionId)
                .name(DEFAULT_NAME)
                .build();
    }
}
