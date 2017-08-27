package com.vaadin.gradlevaadin.day02.repository.generator;

import com.vaadin.gradlevaadin.day02.constant.RoleTypeEnum;
import com.vaadin.gradlevaadin.day02.entity.UserInfo;
import com.vaadin.gradlevaadin.day02.entity.menu.Navi;
import com.vaadin.gradlevaadin.day02.entity.menu.VaadinNavigator;
import com.vaadin.gradlevaadin.day02.repository.UserDataRepository;
import com.vaadin.gradlevaadin.day02.views.AboutView;
import com.vaadin.gradlevaadin.day02.views.DashboardView;
import com.vaadin.gradlevaadin.day02.views.SessionView;
import com.vaadin.gradlevaadin.day02.views.UserView;
import com.vaadin.server.FontAwesome;

import javax.management.relation.Role;

public class LodingDataGenerator {
    static {
        createUsers();
        createNavis();
    }
    private static void createUsers() {
        UserDataRepository userData = UserDataRepository.getInstance();
        userData.save(new UserInfo("user1", "1234", "userNick1", RoleTypeEnum.User));
        userData.save(new UserInfo("user2", "1234", "userNick2", RoleTypeEnum.User));
        userData.save(new UserInfo("user3", "1234", "userNick3", RoleTypeEnum.User));
        userData.save(new UserInfo("admin", "1234", "admins", RoleTypeEnum.Admin));
    }
    private static void createNavis() {
        VaadinNavigator.naviMaps.put("Dashboard", new Navi(DashboardView.VIEW_NAME, "", DashboardView.class, FontAwesome.HOME, RoleTypeEnum.User));
        VaadinNavigator.naviMaps.put("Session", new Navi(SessionView.VIEW_NAME, "session", SessionView.class, FontAwesome.CUBE, RoleTypeEnum.User));
        VaadinNavigator.naviMaps.put("About", new Navi(AboutView.VIEW_NAME, "about", AboutView.class, FontAwesome.INFO, RoleTypeEnum.User));
        VaadinNavigator.naviMaps.put("User", new Navi(UserView.VIEW_NAME, "user", DashboardView.class, FontAwesome.USERS, RoleTypeEnum.Admin));
    }
}
