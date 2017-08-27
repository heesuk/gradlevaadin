package com.vaadin.gradlevaadin.day02.repository;

import com.vaadin.gradlevaadin.day02.entity.UserInfo;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class UserDataRepository implements UserData<UserInfo>{
    private static volatile UserDataRepository INSTANCE = null;
    private Map<Long, UserInfo> users;
    private AtomicLong nextId;

    private UserDataRepository() {
        nextId = new AtomicLong();
        users = new LinkedHashMap<>();
    }

    public synchronized static UserDataRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (UserDataRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDataRepository();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public UserInfo findOne(long id) {
        UserInfo user = users.get(id);
        if (user != null)
            return user;
        return new UserInfo();
    }

    @Override
    public List<UserInfo> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(users.values()));
    }

    @Override
    public int count() {
        return users.size();
    }

    @Override
    public synchronized UserInfo save(UserInfo entity) {
        UserInfo checkUser;
        if (entity.getUserId() == null) {
            checkUser = findByNameOrNickName(entity.getUsername(), entity.getUserNickname());
            if (checkUser.getUserId() != null) {
                throw new IllegalArgumentException("Duplicated user name or user nickname");
            }
            entity.setUserId(nextId.incrementAndGet());
            users.put(entity.getUserId(), entity);
            return entity;
        }
        checkUser = findByName(entity.getUsername());
        if (users.containsKey(entity.getUserId())) {
            if (entity.getUserId() != checkUser.getUserId() && entity.getUsername().equals(checkUser.getUsername())) {
                throw new IllegalArgumentException("Dupicate user name");
            }
            users.put(entity.getUserId(), entity);
            return entity;
        }
        throw new IllegalArgumentException("No user with id "+ entity.getUserId() + " found");
    }

    @Override
    public synchronized void delete(long id) {
        UserInfo user = findOne(id);
        if (user == null) {
            throw new IllegalArgumentException("User with id "+ id + " not found");
        }
        users.remove(user.getUserId());
    }

    public synchronized UserInfo findByName(String name) {
        List<UserInfo> users = findAll();
        for (UserInfo user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return new UserInfo();
    }

    public synchronized UserInfo findByNameOrNickName(String name, String nickName) {
        List<UserInfo> users = findAll();
        for (UserInfo user : users) {
            if (user.getUsername().equals(name) || user.getUserNickname().equals(nickName)) {
                return user;
            }
        }
        return new UserInfo();
    }
    public synchronized UserInfo findByUserNameAndPassword(String username, String password) {
        List<UserInfo> users = findAll();
        for (UserInfo user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return new UserInfo();
    }
//    public synchronized UserInfo findBy
}
