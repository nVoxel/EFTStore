package com.voxeldev.eftstore.web.servlets;

import com.voxeldev.eftstore.services.interfaces.AvatarsService;
import com.voxeldev.eftstore.utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Avatar Delete", value = "/profile/avatar/delete")
public class AvatarDeleteServlet extends HttpServlet {
    
    private AvatarsService avatarsService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        avatarsService = (AvatarsService) config.getServletContext().getAttribute("AvatarsService");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        avatarsService.removeAvatar(ServletUtils.getSessionUser(req));
        
        resp.sendRedirect(ServletUtils.getPathWithContext(req, "/profile"));
    }
}
