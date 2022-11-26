package com.voxeldev.eftstore.services.interfaces;

public interface PasswordsService {
    
    boolean checkPassword(String password, String passwordHash);
    
    String getPasswordHash(String password);
}
