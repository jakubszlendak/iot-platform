package com.jmssolutions.iot.events;

import com.jmssolutions.iot.domain.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * Created by jakub on 30.01.16.
 */
public class OnRegisterCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final User user;

    public OnRegisterCompleteEvent(User user, Locale locale, String appUrl){
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public User getUser() {
        return user;
    }
}
