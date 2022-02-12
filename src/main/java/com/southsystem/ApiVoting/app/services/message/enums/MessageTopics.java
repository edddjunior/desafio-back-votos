package com.southsystem.ApiVoting.app.services.message.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MessageTopics {

    VOTE("vote"),
    VOTE_RETURN("vote_return");

    public final String name;
}
