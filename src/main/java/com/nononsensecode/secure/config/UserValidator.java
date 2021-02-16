package com.nononsensecode.secure.config;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserValidator {
    private final Map<Integer, String> users = Stream.of(new String[][] {
        {"100", "Kaushik"},
        {"101", "Shan"},
        {"102", "Ashif"},
    }).collect(Collectors.toMap(data -> Integer.parseInt(data[0]), data -> data[1]));

    public boolean validate(Integer userId) {
        System.out.println("userId = " + userId);
        return users.containsKey(userId);
    }

    public String userName(Integer userId) {
        if (users.containsKey(userId)) {
            return users.get(userId);
        }

        return null;
    }
}
