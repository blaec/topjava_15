package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserServiceTest;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles("datajpa")
public class DataJpaUserServiceTest extends UserServiceTest {

    @Test
    public void getWithMeals() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
        List<Meal> meals = user.getMeals().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
        MealTestData.assertMatch(meals, MealTestData.MEALS);
    }

}
