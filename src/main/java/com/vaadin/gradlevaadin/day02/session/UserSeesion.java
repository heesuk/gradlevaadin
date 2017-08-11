package com.vaadin.gradlevaadin.day02.session;

import com.vaadin.gradlevaadin.day02.entity.UserInfo;
import com.vaadin.gradlevaadin.day02.exception.UserNotFoundException;
import com.vaadin.gradlevaadin.day02.repository.UserInfoRepository;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import static com.vaadin.server.Page.getCurrent;

@Component
@Slf4j
public class UserSeesion implements Serializable {
    @Autowired
    private UserInfoRepository userInfoRepository;


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
        UserInfo userInfo = userInfoRepository.findByUserIdAndPassword(userId, password);   // find user
        if (userInfo == null || userInfo.getUserId()==null) {
            throw new UserNotFoundException("user not found");
        }

        setUser(userInfo);
    }

    public static void signout() {
        getCurrentSession().invalidate();
        getCurrent().reload();
    }

    public void modifyPassword(String userId, String modifypassword) {
        UserInfo userInfo = userInfoRepository.findByUserId(userId);
        userInfo.setPassword(modifypassword);
        userInfoRepository.save(userInfo);

        com.vaadin.ui.Notification.show("재로그인 하세요 ");
        signout();
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
