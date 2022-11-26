package com.voxeldev.eftstore.services.interfaces;

import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.models.UserSignUp;
import com.voxeldev.eftstore.models.enums.SignUpErrorCode;

public interface SignUpService {
    void signUpUser(UserSignUp user, SignUpResultHandler signUpResultHandler);
    
    interface SignUpResultHandler {
        void onSignUpSuccess(User user);
        
        void onSignUpFailed(SignUpErrorCode errorCode);
    }
}
