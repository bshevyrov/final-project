package ua.com.company.listener;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;

public class RequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequestListener.super.requestInitialized(sre);
    }
}
