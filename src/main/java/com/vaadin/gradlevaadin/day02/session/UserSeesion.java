package com.vaadin.gradlevaadin.day02.session;

import java.io.Serializable;

import com.vaadin.gradlevaadin.day02.entity.UserInfo;
import com.vaadin.gradlevaadin.day02.repository.UserNotFoundException;
import com.vaadin.server.WrappedSession;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;

import static com.vaadin.server.Page.getCurrent;

public class UserSeesion implements Serializable {
    private static UserSeesion INSTANCE = null;

    /*public UserSeesion() {
        if (INSTANCE == null) {
            INSTANCE = new UserSeesion();
        }
    }*/


    public static final String SESSION_KEY = UserSeesion.class.getCanonicalName();

    // 현재 세션 사용자 정보 가져오기
    public static UserInfo getUser() {
        UserInfo userInfo = (UserInfo) getCurrentSession().getAttribute(SESSION_KEY);
        return userInfo;
    }

    public static void setUser(UserInfo user) {
        if (user == null) {
            getCurrentSession().removeAttribute(SESSION_KEY);
        } else {
            getCurrentSession().setAttribute(SESSION_KEY, user);
        }
    }

    public static boolean isSigndIn() {
        return getUser() != null;
    }

    public void signIn(String userId, String password) {
        UserInfo userInfo = new UserInfo(userId, password);       // find user data
        if (userInfo.getUserId()==null) {
            throw new UserNotFoundException("user not found");
        }

        setUser(userInfo);
    }

    public static void signout() {
        getCurrentSession().invalidate();
        getCurrent().reload();
    }

    private static WrappedSession getCurrentSession() {
        VaadinRequest request = VaadinService.getCurrentRequest();
        if (request == null) {
            throw new IllegalStateException("No request bound to current thread");
        }
        WrappedSession session = request.getWrappedSession();
        if (session == null)
            throw new IllegalStateException("No Session bound to current thread");

        return session;
    }
}
