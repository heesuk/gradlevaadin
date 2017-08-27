package com.vaadin.gradlevaadin.day02.entity.menu;

import com.vaadin.gradlevaadin.day02.constant.RoleTypeEnum;
import com.vaadin.gradlevaadin.day02.session.UserSession;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

import java.util.LinkedHashMap;
import java.util.Map;

public class VaadinNavigator extends Navigator {
    public static final Map<String, Navi> naviMaps = new LinkedHashMap<>();

    private Map<String, Navi> activeNaviMaps;

    public VaadinNavigator(UI ui, ComponentContainer container) {
        super(ui, container);

        final RoleTypeEnum userRoleType = UserSession.getUser().getRole();

        activeNaviMaps = new LinkedHashMap<>();
        for (Navi item : naviMaps.values()) {
            if (userRoleType.ordinal() >= item.getRoleType().ordinal()) {
                super.addView(item.getFragment(), item.getViewClass());
                activeNaviMaps.put(item.getFragment(), item);
            }
        }
    }
    public Map<String, Navi> getActiveNaviMaps() {
        return activeNaviMaps;
    }
}
