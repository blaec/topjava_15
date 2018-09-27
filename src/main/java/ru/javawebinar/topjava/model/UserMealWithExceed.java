package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class UserMealWithExceed implements Comparable {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    // https://stackoverflow.com/questions/15578981/how-can-i-put-new-line-n-in-tostring
    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}' + "\n";
    }

    @Override
    public int compareTo(Object o) {
        UserMealWithExceed otherMeal = (UserMealWithExceed) o;
        return dateTime.compareTo(otherMeal.dateTime);
    }
}
