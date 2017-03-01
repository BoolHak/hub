package com.youmait.lib.hub;

public class HubLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("DEBUG HUB: " + message);
    }
}
