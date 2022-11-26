package com.voxeldev.eftstore.dao.interfaces;

import com.voxeldev.eftstore.models.Avatar;

import java.util.Optional;

public interface AvatarsRepository {
    void insertAvatar(Avatar avatar);
    
    Optional<Avatar> getAvatar(int id);
    
    void updateAvatar(Avatar avatar);
    
    void deleteAvatar(int id);
    
}
