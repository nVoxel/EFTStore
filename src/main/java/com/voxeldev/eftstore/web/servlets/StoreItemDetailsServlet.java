package com.voxeldev.eftstore.web.servlets;

import com.voxeldev.eftstore.dao.interfaces.StoreItemsRepository;
import com.voxeldev.eftstore.models.StoreItem;
import com.voxeldev.eftstore.utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Item Details", value = "/item-details")
public class StoreItemDetailsServlet extends HttpServlet {
    
    private StoreItemsRepository storeItemsRepository;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        storeItemsRepository = (StoreItemsRepository) config.getServletContext().getAttribute("StoreItemsRepository");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String storeItemId = req.getParameter("storeItemId");
        
        int storeItemIdInt;
        try {
            storeItemIdInt = Integer.parseInt(storeItemId);
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            resp.getWriter().println("Invalid store item id");
            return;
        }
        
        ServletUtils.setRequestUser(req, ServletUtils.getSessionUser(req));
        
        Optional<StoreItem> storeItem = storeItemsRepository.getItem(storeItemIdInt);
        
        if (!storeItem.isPresent()) {
            resp.setStatus(404);
            resp.getWriter().println("Store item not found");
            return;
        }
        
        req.setAttribute("item", storeItem.get());
        
        getServletContext().getRequestDispatcher("/WEB-INF/view/store-item-details.jsp").forward(req, resp);
    }
}
