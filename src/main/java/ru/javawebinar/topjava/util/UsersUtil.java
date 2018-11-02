package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public final static Integer USER_ID =100000;
    public final static Integer ADMIN_ID =100001;

    public static final List<User> USERS = Arrays.asList(
            new User(USER_ID, "user", "user@mail.us", "userpass", Role.ROLE_USER),
            new User(ADMIN_ID, "admin", "amdin@mail.us", "adminpass", Role.ROLE_USER, Role.ROLE_ADMIN)
    );
}
