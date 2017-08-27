package com.vaadin.gradlevaadin.day02.views;

import com.vaadin.gradlevaadin.day02.entity.Question;
import com.vaadin.gradlevaadin.day02.push.MessageEventBus;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import static com.vaadin.navigator.ViewChangeListener.*;

public class DashboardView extends VerticalLayout implements View, MessageEventBus.EventBusListener {
    public static final String VIEW_NAME = "";
    private static final MessageEventBus eventBus = new MessageEventBus();

    public DashboardView() {
        addComponent(createTopBar());

        addComponent(createButton());
//        new LoginThread().run();
    }
    public HorizontalLayout createButton() {
        Button sendBtn = new Button("SEND");
        sendBtn.addClickListener(e->send());

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setSpacing(true);
        topLayout.setWidth(100, Unit.PERCENTAGE);
        topLayout.addComponents(sendBtn);
        topLayout.setComponentAlignment(sendBtn, Alignment.MIDDLE_LEFT);

        return topLayout;
    }
    private void send() {
//        sendMessage();
    }
//    private void sendMessage() {
//        eventBus.post(question);
//    }
    public HorizontalLayout createTopBar() {
        Label title = new Label("Deshboard");
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
    public void enter(ViewChangeEvent event) {

    }

    @Override
    public void attach() {
        eventBus.register(this);
        super.attach();
    }
    @Override
    public void detach() {
        eventBus.unregister(this);
        super.detach();
    }

    @Override
    public void receive(Question message) {

    }

    class LoginThread extends Thread {
        int count = 0;

        @Override
        public void run() {
            try {
                while (count<100) {
                    Thread.sleep(1000);
                    UI.getCurrent().access(new Runnable() {
                        @Override
                        public void run() {
                            count ++;
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
