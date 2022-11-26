package com.voxeldev.eftstore.web.servlets;

import com.voxeldev.eftstore.dao.interfaces.CartItemsRepository;
import com.voxeldev.eftstore.models.CartItem;
import com.voxeldev.eftstore.models.StoreItem;
import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.services.interfaces.CartItemsService;
import com.voxeldev.eftstore.utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Cart", value = "/cart")
public class CartServlet extends HttpServlet {
    
    private CartItemsRepository cartItemsRepository;
    private CartItemsService cartItemsService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        cartItemsRepository = (CartItemsRepository) config.getServletContext().getAttribute("CartItemsRepository");
        cartItemsService = (CartItemsService) config.getServletContext().getAttribute("CartItemsService");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ServletUtils.getSessionUser(req);
        
        List<CartItem> cartItems = cartItemsRepository.getUserCartItems(user);
        List<StoreItem> storeItems = cartItemsService.getStoreItemsByCartItems(cartItems);
        
        req.setAttribute("cartItems", cartItems);
        req.setAttribute("storeItems", storeItems);
        
        getServletContext().getRequestDispatcher("/WEB-INF/view/cart.jsp").forward(req, resp);
    }
}
