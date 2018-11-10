package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_2;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal actual = service.get(USER_MEAL_ID_1, USER_ID);
        assertThat(actual).isEqualTo(USER_MEAL_1);
    }

    @Test (expected = NotFoundException.class)
    public void getNotFound() {
        service.get(USER_MEAL_ID_1, ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(USER_MEAL_ID_1, USER_ID);
        assertThat(service.getAll(USER_ID)).isEqualTo(Arrays.asList(USER_MEAL_6, USER_MEAL_5, USER_MEAL_4, USER_MEAL_3, USER_MEAL_2));
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        service.delete(USER_MEAL_ID_1, ADMIN_ID);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> meals = service.getBetweenDateTimes(LocalDateTime.of(2015, 5, 30, 0, 0), LocalDateTime.of(2015, 5, 30, 23, 59), USER_ID);
        assertThat(meals).isEqualTo(Arrays.asList(USER_MEAL_3, USER_MEAL_2, USER_MEAL_1));
    }

    @Test
    public void getAll() {
        assertThat(service.getAll(USER_ID)).isEqualTo(Arrays.asList(USER_MEAL_6, USER_MEAL_5, USER_MEAL_4, USER_MEAL_3, USER_MEAL_2, USER_MEAL_1));
    }

    @Test
    public void update() {
        Meal updated = getUpdatedMeal();
        service.update(updated, USER_ID);
        assertThat(service.get(USER_MEAL_ID_1, USER_ID)).isEqualTo(updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        service.update(getUpdatedMeal(), ADMIN_ID);
    }

    private Meal getUpdatedMeal() {
        Meal updated = new Meal(USER_MEAL_1);
        updated.setDescription("updated");
        updated.setCalories(111);
        return updated;
    }

    @Test
    public void create() {
        Meal created = new Meal(LocalDateTime.of(2018, 11, 10, 22, 38), "created", 555);
        service.create(created, ADMIN_ID);
        assertThat(service.getAll(ADMIN_ID)).isEqualTo(Arrays.asList(created, ADMIN_MEAL_2, ADMIN_MEAL_1));
    }

    @Test(expected = DuplicateKeyException.class)
    public void createDuplicate() {
        Meal duplicateMeal = new Meal(ADMIN_MEAL_1.getDateTime(), "duplicate dateTime", 555);
        service.create(duplicateMeal, ADMIN_ID);
    }
}