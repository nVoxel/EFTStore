package com.voxeldev.eftstore.services.impls;

import com.voxeldev.eftstore.services.interfaces.PasswordsService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class PasswordsServiceImpl implements PasswordsService {
    
    @Override
    public boolean checkPassword(String password, String passwordHash) {
        return getPasswordHash(password).equals(passwordHash);
    }
    
    @Override
    public String getPasswordHash(String password) {
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            final byte[] hashBytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            
            final StringBuilder hashStringBuilder = new StringBuilder();
            for (byte hashByte : hashBytes) {
                final String hex = Integer.toHexString(0xff & hashByte);
                
                if (hex.length() == 1)
                    hashStringBuilder.append('0');
                
                hashStringBuilder.append(hex);
            }
            
            return hashStringBuilder.toString();
        } catch (Exception exception) {
            System.out.println("Failed to get password hash of password: " + password);
            exception.printStackTrace();
            
            return null;
        }
    }
}
