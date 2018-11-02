package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User("user", "user@mail.us", "userpass", Role.ROLE_USER),
            new User("admin", "amdin@mail.us", "adminpass", Role.ROLE_ADMIN),
            new User("multi", "multi@mail.us", "multipass", Role.ROLE_USER, Role.ROLE_ADMIN)
    );
}
