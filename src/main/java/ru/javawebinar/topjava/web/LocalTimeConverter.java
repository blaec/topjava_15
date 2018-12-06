package ru.javawebinar.topjava.web;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;

public class LocalTimeConverter implements Converter<String, LocalTime> {
    /*
        If you are using Spring Framework 4.1 (or newer), you don’t have to ensure that the source string is not null
        because Spring Framework guarantees that it is never null. However, if you use Spring 4.0 (or older),
        you might want to check that the source string is not null because the Javadoc of the
        Converter<S, T> interface doesn’t state that the method parameter of the convert() method cannot be null.
    */
    @Override
    public LocalTime convert(String source) {
        return source == null || source.isEmpty() ? null : LocalTime.parse(source);
    }
}
