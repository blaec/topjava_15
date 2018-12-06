package ru.javawebinar.topjava.web;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class LocalDateConverter implements Converter<String,  LocalDate> {
    @Override
    public LocalDate convert(String source) {
        return source == null || source.isEmpty() ? null : LocalDate.parse(source);
    }
}
