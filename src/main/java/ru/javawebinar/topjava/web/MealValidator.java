package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

@Component
public class MealValidator implements Validator {

    private final MealService service;

    @Autowired
    public MealValidator(MealService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Meal.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Meal meal = (Meal) target;
        try {
            if (service.getBetweenDateTimes(meal.getDateTime(), meal.getDateTime(), SecurityUtil.authUserId()).size() > 0) {
                errors.rejectValue("dateTime", "", "Meal with datetime: " + meal.getDateTime() + " already exists");
            }
        } catch (Exception ignored) {
        }
    }
}
