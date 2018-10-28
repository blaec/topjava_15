package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class MealRepository implements Repository {

    private static Map<Integer, Meal> meals = new HashMap<>();

    static {
        meals.put(1000000, new Meal(100000, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.put(1000001, new Meal(100001, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.put(1000002, new Meal(100002, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.put(1000003, new Meal(100003, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.put(1000004, new Meal(100004, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.put(1000005, new Meal(100005, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(meal.getNextId());
        }
        return meals.put(meal.getId(), meal);
    }

    @Override
    public boolean delete(int id) {
        return meals.remove(id) != null;
    }

    @Override
    public Meal create(LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(null, dateTime, description, calories);
        meal.setId(meal.getNextId());
        return meals.put(meal.getId(), meal);
    }

    @Override
    public Meal update(Meal newMeal, int id) {
        Meal updatedMeal = getById(id);
        updatedMeal.setCalories(newMeal.getCalories());
        updatedMeal.setDateTime(newMeal.getDateTime());
        updatedMeal.setDescription(newMeal.getDescription());
        return meals.replace(id, newMeal);
    }

    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    private Meal getById(int id) {
        return Objects.requireNonNull(meals.get(id));
    }
}
