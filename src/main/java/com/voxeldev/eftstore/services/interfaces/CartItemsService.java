package com.voxeldev.eftstore.services.interfaces;

import com.voxeldev.eftstore.models.CartItem;
import com.voxeldev.eftstore.models.StoreItem;

import java.util.List;

public interface CartItemsService {
    List<StoreItem> getStoreItemsByCartItems(List<CartItem> cartItems);
}
