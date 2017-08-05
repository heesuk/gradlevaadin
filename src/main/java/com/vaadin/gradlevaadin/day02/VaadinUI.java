package com.vaadin.gradlevaadin.day02;

import com.vaadin.gradlevaadin.day02.components.LoginComponent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.annotations.VaadinServletConfiguration;

import javax.servlet.annotation.WebServlet;

@SpringUI
public class VaadinUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        setContent(new LoginComponent());
//        setContent(new Button("Click me", e -> Notification.show("Hello Spring+Vaadin user!")));
    }
}
