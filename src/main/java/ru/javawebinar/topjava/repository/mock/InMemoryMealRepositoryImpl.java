package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
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
            log.info("create meal {}", meal);
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
        log.info("delete meal with id {} for user {}", id, userId);
        return getMealsOfRegisteredUser(userId).remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get meal with id {} for user {}", id, userId);
        return getMealsOfRegisteredUser(userId).get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        log.info("get all meals for user {}", userId);
        return getMealsOfRegisteredUser(userId).values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getFiltered(LocalDate dateFrom, LocalDate dateTo, LocalTime timeFrom, LocalTime timeTo, int userId) {
        return getAll(userId).stream()
                .filter(meal -> DateTimeUtil.isBetweenDate(meal.getDate(), dateFrom, dateTo))
                .filter(meal -> DateTimeUtil.isBetweenTime(meal.getTime(), timeFrom, timeTo))
                .collect(Collectors.toList());
    }

    private Map<Integer, Meal> getMealsOfRegisteredUser(int userId) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals != null ? userMeals : new ConcurrentHashMap<>();
    }
}

