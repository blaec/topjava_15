package ru.javawebinar.topjava.model;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseEntity {
    private Integer id;
    private AtomicInteger nextId = new AtomicInteger(100006);

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public int getNextId() {
        return nextId.getAndIncrement();
    }

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return id == null;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
