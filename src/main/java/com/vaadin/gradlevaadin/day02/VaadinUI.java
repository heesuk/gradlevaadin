package com.vaadin.gradlevaadin.day02;

import com.vaadin.gradlevaadin.day02.components.LoginComponent;
import com.vaadin.gradlevaadin.day02.components.MainComponent;
import com.vaadin.gradlevaadin.day02.session.UserSeesion;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class VaadinUI extends UI {
    @Autowired
    private LoginComponent loginComponent;

    /*@Autowired
    private MainComponent mainComponent;
*/
    @Autowired
    UserSeesion userSeesion;

    @Override
    protected void init(VaadinRequest request) {
        // request address and signIn address
        if (userSeesion.isSigndIn()) {
            setContent(new MainComponent());
//            setContent(mainComponent);
            return ;
        }
        // holder ? ? ?
        setContent(loginComponent);
//        setContent(new Button("Click me", e -> Notification.show("Hello Spring+Vaadin user!")));
    }
}
