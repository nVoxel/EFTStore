package com.voxeldev.eftstore.web.servlets;

import com.voxeldev.eftstore.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Profile", value = "/profile")
public class ProfileServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.setRequestUser(req, ServletUtils.getSessionUser(req));
        
        getServletContext().getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
    }
}
