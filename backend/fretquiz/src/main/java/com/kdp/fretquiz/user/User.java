package com.kdp.fretquiz.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kdp.fretquiz.user.ImmutableUser;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableUser.class)
public abstract class User
{
    public abstract Long id();
    public abstract String name();

    @JsonIgnore
    public abstract String sessionId();

    public static final String DEFAULT_NAME = "anon";
}
