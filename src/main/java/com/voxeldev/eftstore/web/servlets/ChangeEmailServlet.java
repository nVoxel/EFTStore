package com.voxeldev.eftstore.web.servlets;

import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.models.enums.ChangeCredentialsErrorCode;
import com.voxeldev.eftstore.services.interfaces.UsersService;
import com.voxeldev.eftstore.utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Change Email", value = "/profile/change-email")
public class ChangeEmailServlet extends HttpServlet {
    
    private UsersService usersService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        usersService = (UsersService) config.getServletContext().getAttribute("UsersService");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.setRequestUser(req, ServletUtils.getSessionUser(req));
        getServletContext().getRequestDispatcher("/WEB-INF/view/change-email.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String newEmail = req.getParameter("email");
        
        usersService.changeEmail(
                ServletUtils.getSessionUser(req), newEmail, getChangeCredentialsResultHandler(req, resp));
    }
    
    private UsersService.ChangeCredentialsResultHandler getChangeCredentialsResultHandler(
            HttpServletRequest req, HttpServletResponse resp
    ) {
        return new UsersService.ChangeCredentialsResultHandler() {
            @Override
            public void onChangeCredentialsSuccess(User user) {
                try {
                    resp.sendRedirect(ServletUtils.getPathWithContext(req, "/profile"));
                } catch (IOException ioException) {
                    System.out.println("Failed to redirect user");
                    ioException.printStackTrace();
                }
            }
            
            @Override
            public void onChangeCredentialsFailed(ChangeCredentialsErrorCode errorCode) {
                try {
                    switch (errorCode) {
                        case INCORRECT_CREDENTIALS:
                            req.setAttribute("isIncorrectCredentials", true);
                            getServletContext().getRequestDispatcher("/WEB-INF/view/change-email.jsp").forward(req, resp);
                            break;
                        case ALREADY_EXISTS:
                            req.setAttribute("isEmailAlreadyExists", true);
                            getServletContext().getRequestDispatcher("/WEB-INF/view/change-email.jsp").forward(req, resp);
                            break;
                        case SERVER_ERROR:
                            System.out.println("Failed to change email due to server error");
                            break;
                    }
                } catch (IOException ioException) {
                    System.out.println("Failed to redirect user");
                    ioException.printStackTrace();
                } catch (ServletException servletException) {
                    System.out.println("Failed to forward user");
                    servletException.printStackTrace();
                }
            }
        };
    }
}
