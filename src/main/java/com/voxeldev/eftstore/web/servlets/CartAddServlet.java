package com.voxeldev.eftstore.web.servlets;

import com.voxeldev.eftstore.dao.interfaces.CartItemsRepository;
import com.voxeldev.eftstore.dao.interfaces.StoreItemsRepository;
import com.voxeldev.eftstore.models.CartItem;
import com.voxeldev.eftstore.models.StoreItem;
import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Add to Cart", value = "/cart/add")
public class CartAddServlet extends HttpServlet {
    
    private CartItemsRepository cartItemsRepository;
    private StoreItemsRepository storeItemsRepository;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        cartItemsRepository = (CartItemsRepository) config.getServletContext().getAttribute("CartItemsRepository");
        storeItemsRepository = (StoreItemsRepository) config.getServletContext().getAttribute("StoreItemsRepository");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = ServletUtils.getSessionUser(req);
        String itemId = req.getParameter("itemId");
        
        int itemIdInt;
        try {
            itemIdInt = Integer.parseInt(itemId);
        } catch (NumberFormatException e) {
            setInvalidItemId(resp);
            return;
        }
        
        Optional<StoreItem> item = storeItemsRepository.getItem(itemIdInt);
        if (!item.isPresent()) {
            setInvalidItemId(resp);
            return;
        }
        
        cartItemsRepository.insertCartItem(
                CartItem.builder()
                        .userId(user.getId())
                        .itemId(itemIdInt)
                        .build()
        );
    }
    
    private void setInvalidItemId(HttpServletResponse resp) throws IOException {
        resp.setStatus(400);
        resp.getWriter().println("Invalid item id");
    }
}
