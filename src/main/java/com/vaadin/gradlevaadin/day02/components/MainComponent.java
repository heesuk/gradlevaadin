package com.vaadin.gradlevaadin.day02.components;

import com.vaadin.event.ShortcutAction;
import com.vaadin.gradlevaadin.day02.session.UserSeesion;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

// HorizontalLayout
//@SpringComponent
public class MainComponent extends VerticalLayout {
    @Autowired
    UserSeesion userSeesion;

    public MainComponent() {
        Label label = new Label(userSeesion.getUser().getUserId() +" 님 안녕하세요 :)");

        final VerticalLayout loginPanel = new VerticalLayout();

        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        loginPanel.addComponent(label);
        loginPanel.addComponent(buildFields());

        Button signOutBtn = new Button("Sign Out");

        signOutBtn.addClickListener(e->signOut());
        loginPanel.addComponent(signOutBtn);
        addComponent(loginPanel);
    }

    private Component buildFields() {
        PasswordField password = new PasswordField("Password");
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        password.setIcon(FontAwesome.LOCK);

        Button changeBtn = new Button("Change");
        changeBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        changeBtn.focus();
        changeBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        changeBtn.addClickListener(e -> modify(userSeesion.getUser().getUserId(), password.getValue()));

        HorizontalLayout fields = new HorizontalLayout();

        fields.setSpacing(true);
        fields.addComponents(password, changeBtn);
        fields.setComponentAlignment(changeBtn, Alignment.BOTTOM_LEFT);

        return fields;
    }

    private void modify(String userId, String password) {
        userSeesion.modifyPassword(userId, password);
    }
    private void signOut() {
        userSeesion.signout();
    }
}
