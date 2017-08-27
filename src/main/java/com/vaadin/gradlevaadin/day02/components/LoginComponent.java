package com.vaadin.gradlevaadin.day02.components;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.gradlevaadin.day02.session.UserSession;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class LoginComponent extends VerticalLayout {
    UserSession userSession;
    Label errorLabel ;

    public LoginComponent() {
        userSession = new UserSession();
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

        errorLabel = new Label();
        errorLabel.addStyleName(ValoTheme.LABEL_FAILURE);
        errorLabel.setVisible(false);

        final VerticalLayout labels = new VerticalLayout();
        labels.addComponent(titleLabel);
        labels.addComponent(errorLabel);
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
            userSession.signIn(id, password);
            Page.getCurrent().reload();
//            Notification.show("success");
        } catch (Exception e) {
//            Notification.show("error");
            errorLabel.setValue(String.format("Login Failed : %s", e.getMessage()));
            errorLabel.setVisible(true);
        }
    }
}
