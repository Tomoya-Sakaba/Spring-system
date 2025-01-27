package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    
    ADMIN("1"),
    USER("2"),
    GUEST("3");

    private final String RoleType;
}

