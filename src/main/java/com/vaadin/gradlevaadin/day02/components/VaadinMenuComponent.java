package com.vaadin.gradlevaadin.day02.components;

import com.vaadin.gradlevaadin.day02.entity.menu.VaadinNavigator;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class VaadinMenuComponent extends CssLayout {
    private CssLayout menuPart;

    public VaadinMenuComponent(final VaadinNavigator navigator) {
        setPrimaryStyleName(ValoTheme.MENU_ROOT);

        // menu
        menuPart = new CssLayout();
        menuPart.addStyleName(ValoTheme.MENU_PART);

        final Label title = new Label("<h3>Vaadin <strong>Study</strong></h3>", ContentMode.HTML);
        title.setSizeUndefined();

        final HorizontalLayout top = new HorizontalLayout();
        top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        top.addStyleName(ValoTheme.MENU_TITLE);
        top.setSpacing(true);
        top.addComponent(title);

        menuPart.addComponent(top);
        addComponent(menuPart);
    }
}
