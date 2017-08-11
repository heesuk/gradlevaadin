package com.vaadin.gradlevaadin.day02.components;

import com.vaadin.annotations.Theme;
import com.vaadin.gradlevaadin.day02.exception.UserNotFoundException;
import com.vaadin.gradlevaadin.day02.session.UserSeesion;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Button;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Notification;
import com.vaadin.server.FontAwesome;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
public class LoginComponent extends VerticalLayout {
    @Autowired
    UserSeesion userSeesion;

    Label errorLabel;
    public LoginComponent() {
//        userSeesion = new UserSeesion();
        setSizeFull();
        Component loginForm = buildForm();
        addComponent(loginForm);

        setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

    }
    private Component buildForm() {
        final VerticalLayout loginPanel = new VerticalLayout();

        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        loginPanel.addComponent(buildLabels());
        loginPanel.addComponent(buildFields());
        return loginPanel;
    }
    private Component buildLabels() {
        Label titleLabel = new Label("Welcome to vaadin seminar");
        titleLabel.addStyleName(ValoTheme.LABEL_H4);
        titleLabel.addStyleName(ValoTheme.LABEL_COLORED);

        // error label
        errorLabel = new Label();
        errorLabel.addStyleName(ValoTheme.LABEL_FAILURE);
        errorLabel.setVisible(false);

        VerticalLayout labels = new VerticalLayout();
        labels.addComponents(titleLabel, errorLabel);

        return labels;
    }
    private Component buildFields() {
        final TextField id = new TextField("UserID");
        id.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        id.setIcon(FontAwesome.USER);

        final PasswordField password = new PasswordField("Password");
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        password.setIcon(FontAwesome.LOCK);

        final Button signin = new Button("Sign In");
        signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
        signin.focus();
        signin.setClickShortcut(KeyCode.ENTER);
        signin.addClickListener(e -> signIn(id.getValue(), password.getValue()));

        HorizontalLayout fields = new HorizontalLayout();

        fields.setSpacing(true);
        fields.addComponents(id, password, signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        return fields;
    }
    private void signIn(String id, String password) {
        try {
            userSeesion.signIn(id, password);
            Page.getCurrent().reload();
        } catch (UserNotFoundException e) {
            errorLabel.setValue(String.format("Login Failed : %s", e.getMessage()));
            errorLabel.setVisible(true);
        }
    }
}
