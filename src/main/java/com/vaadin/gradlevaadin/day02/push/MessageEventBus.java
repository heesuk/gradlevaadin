package com.vaadin.gradlevaadin.day02.push;

import com.vaadin.gradlevaadin.day02.entity.Question;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageEventBus implements Serializable {
    private static final List<EventBusListener> listeners = new CopyOnWriteArrayList<EventBusListener>();

    public static synchronized void register(EventBusListener listener) {
        if (listeners.contains(listener)) {
            return ;
        }
        listeners.add(listener);
    }
    public static synchronized void unregister(EventBusListener listener) {
        listeners.remove(listener);
    }
    public synchronized static void send(final Question message) {
        for (final EventBusListener listener : listeners) {
            listener.receive(message);
        }
    }

    public interface EventBusListener {
        public void receive(final Question message);
    }
}
