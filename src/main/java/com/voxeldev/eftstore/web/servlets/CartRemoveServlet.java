package com.voxeldev.eftstore.web.servlets;

import com.voxeldev.eftstore.dao.interfaces.CartItemsRepository;
import com.voxeldev.eftstore.models.CartItem;
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

@WebServlet(name = "Remove from Cart", value = "/cart/remove")
public class CartRemoveServlet extends HttpServlet {
    private CartItemsRepository cartItemsRepository;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        cartItemsRepository = (CartItemsRepository) config.getServletContext().getAttribute("CartItemsRepository");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = ServletUtils.getSessionUser(req);
        String cartItemId = req.getParameter("cartItemId");
        
        int cartItemIdInt;
        try {
            cartItemIdInt = Integer.parseInt(cartItemId);
        } catch (NumberFormatException e) {
            setInvalidCartItemId(resp);
            return;
        }
        
        Optional<CartItem> cartItem = cartItemsRepository.getCartItem(cartItemIdInt);
        if (!cartItem.isPresent() || cartItem.get().getUserId() != user.getId()) {
            setInvalidCartItemId(resp);
            return;
        }
        
        cartItemsRepository.deleteCartItem(cartItem.get());
    }
    
    private void setInvalidCartItemId(HttpServletResponse resp) throws IOException {
        resp.setStatus(400);
        resp.getWriter().println("Invalid cart item id");
    }
}
