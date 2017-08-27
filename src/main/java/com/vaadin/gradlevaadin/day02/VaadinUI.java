package com.vaadin.gradlevaadin.day02;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.gradlevaadin.day02.components.LoginComponent;
import com.vaadin.gradlevaadin.day02.components.MainScreenComponent;
import com.vaadin.gradlevaadin.day02.repository.generator.LodingDataGenerator;
import com.vaadin.gradlevaadin.day02.session.UserSession;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

@SpringUI
//@Push(value= PushMode.AUTOMATIC, transport = Transport.WEBSOCKET)
public class VaadinUI extends UI {
    private static final LodingDataGenerator dataGenerator = new LodingDataGenerator();

    @WebServlet(urlPatterns = "/*", name = "VaddinUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = VaadinUI.class, productionMode = false)
    public static class VaddinUIServlet extends VaadinServlet {

    }
    @Override
    protected void init(VaadinRequest request) {
        if (UserSession.isSigndIn()) {
            setContent(new MainScreenComponent(this));
            getNavigator().navigateTo(getNavigator().getState());
            return ;
        }
        setContent(new LoginComponent());
//        setContent(new Button("Click me", e -> Notification.show("Hello Spring+Vaadin user!")));
    }
}
