package com.voxeldev.eftstore.web.servlets;

import com.voxeldev.eftstore.dao.interfaces.AvatarsRepository;
import com.voxeldev.eftstore.models.Avatar;
import com.voxeldev.eftstore.services.interfaces.AvatarsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Avatars", value = "/files/avatars/*")
public class AvatarServlet extends HttpServlet {
    
    private AvatarsService avatarsService;
    private AvatarsRepository avatarsRepository;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        avatarsService = (AvatarsService) config.getServletContext().getAttribute("AvatarsService");
        avatarsRepository = (AvatarsRepository) config.getServletContext().getAttribute("AvatarsRepository");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int avatarId;
        
        try {
            String avatarIdString = req.getPathInfo().substring(1);
            avatarId = Integer.parseInt(avatarIdString);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            setNotFound(resp);
            return;
        }
        
        try {
            Optional<Avatar> avatar = avatarsRepository.getAvatar(avatarId);
            
            if (!avatar.isPresent()) {
                setNotFound(resp);
                return;
            }
            
            resp.setContentType(avatar.get().getContentType());
            resp.setContentLength(avatar.get().getSize());
            resp.setHeader("Content-Disposition", "filename=\"" + avatar.get().getOriginalFileName() + "\"");
            avatarsService.readAvatarFromStorage(avatar.get(), resp.getOutputStream());
            resp.flushBuffer();
        } catch (Exception e) {
            setNotFound(resp);
        }
    }
    
    private void setNotFound(HttpServletResponse resp) throws IOException {
        resp.setStatus(404);
        resp.getWriter().println("Avatar not found");
    }
}
