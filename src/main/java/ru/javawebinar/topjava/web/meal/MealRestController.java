package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public Meal create(Meal meal) {
        meal.setUserId(SecurityUtil.getAuthUserId());
        log.info("create new meal for user {}", meal.getUserId());
        return service.create(meal);
    }

    public Meal update(Meal meal) {
        meal.setUserId(SecurityUtil.getAuthUserId());
        log.info("update meal with id {} for user {}", meal.getId(), meal.getUserId());
        return service.update(meal);
    }

    public void delete(int id)  {
        log.info("delete meal with id {} for user {}", id, SecurityUtil.getAuthUserId());
        service.delete(id, SecurityUtil.getAuthUserId());
    }

    public Meal get(int id)  {
        log.info("get meal with id {} for user {}", id, SecurityUtil.getAuthUserId());
        return service.get(id, SecurityUtil.getAuthUserId());
    }

    public Collection<Meal> getAll() {
        log.info("get all meals for user {}", SecurityUtil.getAuthUserId());
        return service.getAll(SecurityUtil.getAuthUserId());
    }

    public Collection<Meal> getFiltered(String dateFrom, String dateTo, String timeFrom, String timeTo) {
        LocalDate fromDate = dateFrom.isEmpty() ? LocalDate.MIN : LocalDate.parse(dateFrom);
        LocalDate toDate = dateTo.isEmpty() ? LocalDate.MAX : LocalDate.parse(dateTo);
        LocalTime fromTime = timeFrom.isEmpty() ? LocalTime.MIN : LocalTime.parse(timeFrom);
        LocalTime toTime = timeTo.isEmpty() ? LocalTime.MAX : LocalTime.parse(timeTo);
        log.info("filtering meals from date {} to date {} and between times {} and {}", fromDate, toDate, fromTime, toTime);

        return service.getFiltered(fromDate, toDate, fromTime, toTime, SecurityUtil.getAuthUserId());
    }
}