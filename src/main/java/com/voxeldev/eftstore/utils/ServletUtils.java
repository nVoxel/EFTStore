package com.voxeldev.eftstore.utils;

import com.voxeldev.eftstore.models.User;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {
    
    public static void setRequestUser(
            HttpServletRequest request,
            User user
    ) {
        request.setAttribute("user", user);
    }
    
    public static User getRequestUser(
            HttpServletRequest request
    ) {
        return (User) request.getAttribute("user");
    }
    
    public static void setSessionUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(User.SESSION_ATTRIBUTE_NAME, user);
    }
    
    public static User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(User.SESSION_ATTRIBUTE_NAME);
    }
    
    public static String getPathWithContext(
            HttpServletRequest request,
            String path
    ) {
        return request.getContextPath() + path;
    }
}
