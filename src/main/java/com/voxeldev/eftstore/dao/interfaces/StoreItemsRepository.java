package com.voxeldev.eftstore.dao.interfaces;

import com.voxeldev.eftstore.models.StoreItem;

import java.util.List;
import java.util.Optional;

public interface StoreItemsRepository {
    void insertItem(StoreItem item);
    
    Optional<StoreItem> getItem(int id);
    
    List<StoreItem> getMultipleItems(List<Integer> ids);
    
    List<StoreItem> getAllItems();
}
