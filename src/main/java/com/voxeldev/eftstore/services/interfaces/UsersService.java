package com.voxeldev.eftstore.services.interfaces;

import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.models.UserSignUp;
import com.voxeldev.eftstore.models.enums.ChangeCredentialsErrorCode;

public interface UsersService {
    void saveUser(UserSignUp user);
    
    void changeEmail(User user, String newEmail, ChangeCredentialsResultHandler resultHandler);
    
    void changeNickname(User user, String newNickname, ChangeCredentialsResultHandler resultHandler);
    
    void changePassword(User user, String oldPassword, String newPassword, ChangeCredentialsResultHandler resultHandler);
    
    interface ChangeCredentialsResultHandler {
        void onChangeCredentialsSuccess(User user);
        
        void onChangeCredentialsFailed(ChangeCredentialsErrorCode errorCode);
    }
}
