package com.punch.punchmanagementservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SwipeType
{
    IN("IN"),
    OUT("OUT");

    private final String swipeType;

}
