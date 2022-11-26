package com.voxeldev.eftstore.utils;

public class CredentialsValidationUtils {
    
    public static boolean isValidEmail(String email) {
        return !isNullOrEmpty(email) &&
                email.length() <= 320 &&
                email.matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    }
    
    public static boolean isValidNickname(String nickname) {
        return !isNullOrEmpty(nickname) &&
                nickname.length() >= 4 &&
                nickname.length() <= 32 &&
                nickname.matches("^[a-zA-Z0-9а-яА-Я_\\-]+$");
    }
    
    public static boolean isValidPassword(String password) {
        return !isNullOrEmpty(password) &&
                password.length() >= 8 &&
                password.length() <= 64;
    }
    
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
