package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public Meal create(Meal meal) {
        log.info("create new meal for user {}", meal.getUserId());
        return service.create(meal);
    }

    public Meal update(Meal meal) {
        log.info("update meal with id {} for user {}", meal.getId(), meal.getUserId());
        return service.update(meal);
    }

    public void delete(int id, int userId)  {
        log.info("delete meal with id {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public Meal get(int id, int userId)  {
        log.info("get meal with id {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public Collection<Meal> getAll(int userId) {
        log.info("get all meals for user {}", userId);
        return service.getAll(userId);
    }
}