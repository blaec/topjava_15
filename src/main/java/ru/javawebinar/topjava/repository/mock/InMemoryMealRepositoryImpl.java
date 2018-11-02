package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        Map<Integer, Meal> userMeals = getMealsOfRegisteredUser(meal.getUserId());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            userMeals.put(meal.getId(), meal);
            repository.put(meal.getUserId(), userMeals);
            return meal;
        }
        // treat case: update, but absent in storage
        userMeals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        repository.computeIfPresent(meal.getUserId(), (id, oldMeal) -> userMeals);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return getMealsOfRegisteredUser(userId).remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        return getMealsOfRegisteredUser(userId).get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return getMealsOfRegisteredUser(userId).values();
    }

    private Map<Integer, Meal> getMealsOfRegisteredUser(int userId) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals != null ? userMeals : new ConcurrentHashMap<>();
    }
}

