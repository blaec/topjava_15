package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

public interface Repository {
    Meal save(Meal meal);
    boolean delete(int id);
    Meal create(LocalDateTime dateTime, String description, int calories);
    Meal update(Meal newMeal, int id);
}

