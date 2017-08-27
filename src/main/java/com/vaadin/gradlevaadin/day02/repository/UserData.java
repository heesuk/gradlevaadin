package com.vaadin.gradlevaadin.day02.repository;

import java.util.List;

public interface UserData<T> {
    T findOne(long id);
    List<T> findAll();
    int count();
    T save(T entity);
    void delete(long id);
}
