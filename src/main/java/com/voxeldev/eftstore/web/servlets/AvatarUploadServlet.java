package com.voxeldev.eftstore.web.servlets;

import com.voxeldev.eftstore.models.enums.AvatarsSaveErrorCode;
import com.voxeldev.eftstore.services.interfaces.AvatarsService;
import com.voxeldev.eftstore.utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(name = "Upload Avatar", value = "/profile/avatar/upload")
@MultipartConfig
public class AvatarUploadServlet extends HttpServlet {
    
    private AvatarsService avatarsService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        avatarsService = (AvatarsService) config.getServletContext().getAttribute("AvatarsService");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.setRequestUser(req, ServletUtils.getSessionUser(req));
        
        getServletContext().getRequestDispatcher("/WEB-INF/view/upload-avatar.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part file = req.getPart("avatar");
        
        avatarsService.saveAvatar(file, ServletUtils.getSessionUser(req), getUploadAvatarResultHandler(req, resp));
    }
    
    private AvatarsService.AvatarsSaveResultHandler getUploadAvatarResultHandler(
            HttpServletRequest req, HttpServletResponse resp
    ) {
        return new AvatarsService.AvatarsSaveResultHandler() {
            @Override
            public void onAvatarSaveSuccess() {
                try {
                    resp.sendRedirect(ServletUtils.getPathWithContext(req, "/profile"));
                } catch (IOException ioException) {
                    System.out.println("Failed to redirect user");
                    ioException.printStackTrace();
                }
            }
            
            @Override
            public void onAvatarSaveFailed(AvatarsSaveErrorCode errorCode) {
                try {
                    switch (errorCode) {
                        case WRONG_FILE_SIZE:
                            req.setAttribute("isIncorrectFileSize", true);
                            getServletContext().getRequestDispatcher("/WEB-INF/view/upload-avatar.jsp").forward(req, resp);
                            break;
                        case UNSUPPORTED_CONTENT_TYPE:
                            req.setAttribute("isIncorrectContentType", true);
                            getServletContext().getRequestDispatcher("/WEB-INF/view/upload-avatar.jsp").forward(req, resp);
                            break;
                        case INVALID_FILE:
                            req.setAttribute("isInvalidFile", true);
                            getServletContext().getRequestDispatcher("/WEB-INF/view/upload-avatar.jsp").forward(req, resp);
                            break;
                        case SERVER_ERROR:
                            System.out.println("Failed to upload avatar due to server error");
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
