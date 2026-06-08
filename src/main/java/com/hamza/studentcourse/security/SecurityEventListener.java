package com.hamza.studentcourse.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class SecurityEventListener {

    private static final Logger log =
            LoggerFactory.getLogger(
                    SecurityEventListener.class
            );

    @EventListener
    public void onFailure(
            AuthenticationFailureBadCredentialsEvent event
    ) {

        log.warn(
                "Failed login attempt: {}",
                event.getAuthentication()
                        .getPrincipal()
        );
    }
}