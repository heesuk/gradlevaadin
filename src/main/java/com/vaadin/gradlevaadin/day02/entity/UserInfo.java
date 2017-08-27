package com.vaadin.gradlevaadin.day02.entity;

import com.vaadin.gradlevaadin.day02.constant.RoleTypeEnum;

public class UserInfo {
    private Long userId;
    private String username;
    private String userNickname;
    private String password;
    private RoleTypeEnum role;

    public UserInfo() {
        this.role = RoleTypeEnum.User;
    }
    public UserInfo(UserInfo user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.userNickname = user.getUserNickname();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = RoleTypeEnum.User;
    }

    public UserInfo(String username, String password, String userNickname, RoleTypeEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.userNickname = userNickname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public RoleTypeEnum getRole() {
        return role;
    }
    public void setRole(RoleTypeEnum role) {
        this.role = role;
    }
}
