package com.voxeldev.eftstore.services.impls;

import com.voxeldev.eftstore.dao.interfaces.StoreItemsRepository;
import com.voxeldev.eftstore.models.CartItem;
import com.voxeldev.eftstore.models.StoreItem;
import com.voxeldev.eftstore.services.interfaces.CartItemsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CartItemsServiceImpl implements CartItemsService {
    
    private final StoreItemsRepository storeItemsRepository;
    
    public CartItemsServiceImpl(
            StoreItemsRepository storeItemsRepository
    ) {
        this.storeItemsRepository = storeItemsRepository;
    }
    
    @Override
    public List<StoreItem> getStoreItemsByCartItems(List<CartItem> cartItems) {
        List<Integer> cartItemsIds =
                cartItems.stream()
                        .map(cartItem -> cartItem.getItemId())
                        .collect(Collectors.toList());
        
        List<StoreItem> storeItems = storeItemsRepository.getMultipleItems(cartItemsIds);
        
        return cartItems.stream()
                .map(cartItem -> findInStoreItems(storeItems, cartItem.getItemId()))
                .collect(Collectors.toList());
    }
    
    private StoreItem findInStoreItems(List<StoreItem> storeItems, int itemId) {
        Optional<StoreItem> storeItem = storeItems.stream().filter(item -> item.getId() == itemId).findFirst();
        
        return storeItem.orElse(null);
    }
}
