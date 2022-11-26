package com.voxeldev.eftstore.web.servlets;

import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.models.UserAuthentication;
import com.voxeldev.eftstore.models.enums.AuthenticationErrorCode;
import com.voxeldev.eftstore.services.impls.AuthenticationServiceImpl;
import com.voxeldev.eftstore.services.interfaces.AuthenticationService;
import com.voxeldev.eftstore.utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Sign In", value = "/profile/sign-in")
public class SignInServlet extends HttpServlet {
    
    private AuthenticationService authenticationService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        authenticationService =
                (AuthenticationServiceImpl) config.getServletContext().getAttribute("AuthenticationService");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/sign-in.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        UserAuthentication userAuthentication = new UserAuthentication(email, password);
        
        authenticationService.authenticateUser(req, resp, userAuthentication, getSignInResultHandler(req, resp));
    }
    
    private AuthenticationService.AuthResultHandler getSignInResultHandler(
            HttpServletRequest req, HttpServletResponse resp) {
        return new AuthenticationService.AuthResultHandler() {
            @Override
            public void onAuthSuccess(User user) {
                try {
                    resp.sendRedirect(ServletUtils.getPathWithContext(req, "/profile"));
                } catch (IOException ioException) {
                    System.out.println("Failed to redirect user");
                    ioException.printStackTrace();
                }
            }
            
            @Override
            public void onAuthFailed(AuthenticationErrorCode errorCode) {
                try {
                    switch (errorCode) {
                        case INCORRECT_CREDENTIALS:
                            req.setAttribute("isIncorrectCredentials", true);
                            getServletContext().getRequestDispatcher("/WEB-INF/view/sign-in.jsp").forward(req, resp);
                            break;
                        case USER_NOT_FOUND:
                            req.getSession().setAttribute("redirectedFromSignIn", true);
                            resp.sendRedirect(ServletUtils.getPathWithContext(req, "/profile/sign-up"));
                            break;
                        case SERVER_ERROR:
                            System.out.println("Failed to authorize user due to server error");
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
