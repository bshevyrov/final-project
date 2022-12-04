package ua.com.company.utils;

import jakarta.servlet.http.HttpSession;

public class LoggedPersonThreadLocal {
    private  static final ThreadLocal<HttpSession> currentThreadLocal  = new ThreadLocal<>();

    public static void set(HttpSession session){
        currentThreadLocal.set(session);
    }

    public static HttpSession get(){
         return currentThreadLocal.get();
    }

    public static void unset(){
        currentThreadLocal.remove();
    }

    private LoggedPersonThreadLocal() {
    }
}
