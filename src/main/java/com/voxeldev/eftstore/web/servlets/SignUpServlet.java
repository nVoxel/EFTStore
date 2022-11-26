package com.voxeldev.eftstore.web.servlets;

import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.models.UserSignUp;
import com.voxeldev.eftstore.models.enums.SignUpErrorCode;
import com.voxeldev.eftstore.services.interfaces.SignUpService;
import com.voxeldev.eftstore.utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Sign Up", value = "/profile/sign-up")
public class SignUpServlet extends HttpServlet {
    
    private SignUpService signUpService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        signUpService = (SignUpService) config.getServletContext().getAttribute("SignUpService");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/sign-up.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");
        
        UserSignUp userSignUp = new UserSignUp(email, nickname, password);
        
        signUpService.signUpUser(userSignUp, getSignUpResultHandler(req, resp));
    }
    
    private SignUpService.SignUpResultHandler getSignUpResultHandler(
            HttpServletRequest req, HttpServletResponse resp) {
        return new SignUpService.SignUpResultHandler() {
            @Override
            public void onSignUpSuccess(User user) {
                try {
                    resp.sendRedirect(ServletUtils.getPathWithContext(req, "/profile/sign-in"));
                } catch (IOException ioException) {
                    System.out.println("Failed to redirect user");
                    ioException.printStackTrace();
                }
            }
            
            @Override
            public void onSignUpFailed(SignUpErrorCode errorCode) {
                try {
                    switch (errorCode) {
                        case NICKNAME_ALREADY_REGISTERED:
                        case EMAIL_ALREADY_REGISTERED:
                            req.getSession().setAttribute("redirectedFromSignUp", true);
                            resp.sendRedirect(ServletUtils.getPathWithContext(req, "/profile/sign-in"));
                            break;
                        case INCORRECT_CREDENTIALS:
                            req.setAttribute("isIncorrectCredentials", true);
                            getServletContext().getRequestDispatcher("/WEB-INF/view/sign-up.jsp").forward(req, resp);
                            break;
                        case SERVER_ERROR:
                            System.out.println("Failed to register user due to server error");
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
