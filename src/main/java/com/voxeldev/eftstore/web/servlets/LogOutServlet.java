package com.voxeldev.eftstore.web.servlets;

import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.services.interfaces.SessionsService;
import com.voxeldev.eftstore.utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet(name = "Log Out", value = "/profile/log-out")
public class LogOutServlet extends HttpServlet {
    
    private SessionsService sessionsService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        sessionsService = (SessionsService) config.getServletContext().getAttribute("SessionsService");
    }
    
    @Override
    protected void doGet(
            javax.servlet.http.HttpServletRequest req,
            javax.servlet.http.HttpServletResponse resp
    ) throws java.io.IOException {
        User user = (User) req.getSession().getAttribute(User.SESSION_ATTRIBUTE_NAME);
        
        sessionsService.deleteSession(user.getId());
        
        removeSessionAttribute(req);
        
        resp.sendRedirect(ServletUtils.getPathWithContext(req, "/profile/sign-in"));
    }
    
    private void removeSessionAttribute(HttpServletRequest request) {
        request.getSession().removeAttribute(User.SESSION_ATTRIBUTE_NAME);
    }
}
