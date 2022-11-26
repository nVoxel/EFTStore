package com.voxeldev.eftstore.web.servlets;

import com.voxeldev.eftstore.dao.interfaces.CartItemsRepository;
import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Clear Cart", value = "/cart/clear")
public class CartClearServlet extends HttpServlet {
    private CartItemsRepository cartItemsRepository;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        cartItemsRepository = (CartItemsRepository) config.getServletContext().getAttribute("CartItemsRepository");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = ServletUtils.getSessionUser(req);
        
        cartItemsRepository.deleteCartItems(user.getId());
    }
}
