package com.voxeldev.eftstore.dao.interfaces;

import com.voxeldev.eftstore.models.CartItem;
import com.voxeldev.eftstore.models.User;

import java.util.List;
import java.util.Optional;

public interface CartItemsRepository {
    Optional<CartItem> getCartItem(int id);
    
    List<CartItem> getUserCartItems(User user);
    
    void insertCartItem(CartItem cartItem);
    
    void deleteCartItem(CartItem cartItem);
    
    void deleteCartItems(int userId);
}
