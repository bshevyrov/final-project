package ua.com.company.utils;

import jakarta.servlet.http.HttpSession;

import java.util.List;

public class CurrentSessionsThreadLocal {
    private  static final ThreadLocal<List<HttpSession>> currentThreadLocal  = new ThreadLocal<>();

    public static void set(List<HttpSession> session){
        currentThreadLocal.set(session);
    }

    public static List<HttpSession> get(){
         return currentThreadLocal.get();
    }

    public static void unset(){
        currentThreadLocal.remove();
    }

    private CurrentSessionsThreadLocal() {
    }
}
