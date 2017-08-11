package com.vaadin.gradlevaadin.day02.entity;

import com.vaadin.gradlevaadin.day02.constant.RoleTypeEnum;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "userinfo")
public class UserInfo {
    @Column(name = "userid")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter @Setter
    private String userId;
    @Column(name = "username")
    @Getter@Setter
    private String username;
    @Column(name = "usernickname")
    @Getter@Setter
    private String userNickname;
    @Column(name = "password")
    @Getter@Setter
    private String password;

    @Getter@Setter
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
    public UserInfo(String userid, String password) {
        this.userId = userid;
        this.password = password;
        this.role = RoleTypeEnum.User;
    }
}
