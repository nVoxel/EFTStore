package com.voxeldev.eftstore.dao.interfaces;

import com.voxeldev.eftstore.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {
    void insertUser(User user);
    
    List<User> getAllUsers();
    
    Optional<User> getUser(int id);
    
    Optional<User> getUser(String email);
    
    Optional<User> getUserByNickname(String nickname);
    
    void updateUser(User user);
}
