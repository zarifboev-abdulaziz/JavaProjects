package uz.pdp.controller.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Security {
    public static boolean checkCookies(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();

        if (cookies == null){
            return false;
        }

        String value = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")){
                value = cookie.getValue();
            }
        }

        if (value == null){
            return false;
        }

        if (value.equals("")){
            return false;
        }
        return true;
    }
}
