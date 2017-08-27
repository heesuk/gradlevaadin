package com.vaadin.gradlevaadin.day02.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class UserView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "user";
    public UserView() {
        addComponent(createTopBar());
    }
    public HorizontalLayout createTopBar() {
        Label title = new Label("User");
        title.setSizeUndefined();
        title.setStyleName(ValoTheme.LABEL_H1);
        title.addStyleName(ValoTheme.LABEL_NO_MARGIN);

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setSpacing(true);
        topLayout.setWidth(100, Unit.PERCENTAGE);
        topLayout.addComponents(title);
        topLayout.setComponentAlignment(title, Alignment.MIDDLE_LEFT);

        return topLayout;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
