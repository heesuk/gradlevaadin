package com.vaadin.gradlevaadin.day02.entity.menu;

import com.vaadin.gradlevaadin.day02.constant.RoleTypeEnum;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

public class Navi {
    private String fragment;
    private String viewName;
    private Class<? extends View> viewClass;
    private Resource icon;
    private RoleTypeEnum roleType;

    public Navi(String fragment, String viewName, Class<? extends View> viewClass, Resource icon, RoleTypeEnum roleType){
        this.fragment = fragment;
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.icon = icon;
        this.roleType = roleType;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public void setViewClass(Class<? extends View> viewClass) {
        this.viewClass = viewClass;
    }

    public Resource getIcon() {
        return icon;
    }

    public void setIcon(Resource icon) {
        this.icon = icon;
    }

    public RoleTypeEnum getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleTypeEnum roleType) {
        this.roleType = roleType;
    }
}
