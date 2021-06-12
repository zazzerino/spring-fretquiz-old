package com.kdp.fretquiz.user;

import com.kdp.fretquiz.user.ImmutableUser;
import org.immutables.value.Value;

@Value.Immutable
public abstract class User
{
    public abstract Long id();
    public abstract String sessionId();
    public abstract String name();

    public static final String DEFAULT_NAME = "anon";
}
