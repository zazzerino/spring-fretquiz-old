package com.kdp.fretquiz.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kdp.fretquiz.user.ImmutableUser;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = ImmutableUser.class)
public abstract class User
{
    public abstract Optional<Long> id();
    public abstract String sessionId();
    public abstract String name();

    public static final String DEFAULT_NAME = "anon";
}
