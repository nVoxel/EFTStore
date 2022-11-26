package com.voxeldev.eftstore.services.interfaces;

import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.models.UserAuthentication;
import com.voxeldev.eftstore.models.enums.AuthenticationErrorCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    void authenticateUser(
            HttpServletRequest request, HttpServletResponse response,
            UserAuthentication userAuthentication, AuthResultHandler authResultHandler);
    
    boolean authenticateUser(
            HttpServletRequest request, HttpServletResponse response);
    
    interface AuthResultHandler {
        void onAuthSuccess(User user);
        
        void onAuthFailed(AuthenticationErrorCode errorCode);
    }
}
