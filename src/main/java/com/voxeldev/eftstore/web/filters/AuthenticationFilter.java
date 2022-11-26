package com.voxeldev.eftstore.web.filters;

import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.services.interfaces.AuthenticationService;
import com.voxeldev.eftstore.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    
    AuthenticationService authenticationService;
    
    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext servletContext = filterConfig.getServletContext();
        
        this.authenticationService = (AuthenticationService) servletContext.getAttribute(
                "AuthenticationService"
        );
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        
        if (httpServletRequest.getRequestURI().startsWith(
                ServletUtils.getPathWithContext(httpServletRequest, "/resources"))) {
            chain.doFilter(request, response);
            return;
        }
        
        HttpSession session = httpServletRequest.getSession(false);
        
        boolean isAuthenticated = false;
        
        boolean sessionExists = session != null;
        
        boolean isRequestOnAuthPage =
                httpServletRequest.getRequestURI().equals(
                        ServletUtils.getPathWithContext(httpServletRequest, "/profile/sign-in")) ||
                        httpServletRequest.getRequestURI().equals(
                                ServletUtils.getPathWithContext(httpServletRequest, "/profile/sign-up"));
        
        if (sessionExists) {
            isAuthenticated = session.getAttribute(User.SESSION_ATTRIBUTE_NAME) != null;
        }
        
        if (!isAuthenticated) {
            isAuthenticated = authenticationService.authenticateUser(httpServletRequest, httpServletResponse);
        }
        
        if (!isAuthenticated && !isRequestOnAuthPage) {
            httpServletResponse.sendRedirect(
                    ServletUtils.getPathWithContext(httpServletRequest, "/profile/sign-up")
            );
            return;
        }
        
        if (isAuthenticated && isRequestOnAuthPage) {
            httpServletResponse.sendRedirect(
                    ServletUtils.getPathWithContext(httpServletRequest, "/profile")
            );
            return;
        }
        
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
