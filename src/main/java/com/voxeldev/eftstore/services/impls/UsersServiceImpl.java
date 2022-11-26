package com.voxeldev.eftstore.services.impls;

import com.voxeldev.eftstore.dao.interfaces.UsersRepository;
import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.models.UserSignUp;
import com.voxeldev.eftstore.models.enums.ChangeCredentialsErrorCode;
import com.voxeldev.eftstore.services.interfaces.UsersService;
import com.voxeldev.eftstore.utils.CredentialsValidationUtils;

import java.util.Optional;

public class UsersServiceImpl implements UsersService {
    
    private final UsersRepository usersRepository;
    private final PasswordsServiceImpl passwordsService;
    
    public UsersServiceImpl(UsersRepository usersRepository, PasswordsServiceImpl passwordsService) {
        this.usersRepository = usersRepository;
        this.passwordsService = passwordsService;
    }
    
    @Override
    public void saveUser(UserSignUp user) {
        usersRepository.insertUser(
                User.builder()
                        .email(user.getEmail())
                        .passwordHash(passwordsService.getPasswordHash(user.getPassword()))
                        .nickname(user.getNickname())
                        .build()
        );
    }
    
    @Override
    public void changeEmail(User user, String newEmail, ChangeCredentialsResultHandler resultHandler) {
        if (!CredentialsValidationUtils.isValidEmail(newEmail)) {
            resultHandler.onChangeCredentialsFailed(ChangeCredentialsErrorCode.INCORRECT_CREDENTIALS);
            return;
        }
        
        Optional<User> existingUser = usersRepository.getUser(newEmail);
        
        if (existingUser.isPresent()) {
            resultHandler.onChangeCredentialsFailed(ChangeCredentialsErrorCode.ALREADY_EXISTS);
            return;
        }
        
        user.setEmail(newEmail);
        
        usersRepository.updateUser(user);
        
        resultHandler.onChangeCredentialsSuccess(user);
    }
    
    @Override
    public void changeNickname(User user, String newNickname, ChangeCredentialsResultHandler resultHandler) {
        if (!CredentialsValidationUtils.isValidNickname(newNickname)) {
            resultHandler.onChangeCredentialsFailed(ChangeCredentialsErrorCode.INCORRECT_CREDENTIALS);
            return;
        }
        
        Optional<User> existingUser = usersRepository.getUserByNickname(newNickname);
        
        if (existingUser.isPresent()) {
            resultHandler.onChangeCredentialsFailed(ChangeCredentialsErrorCode.ALREADY_EXISTS);
            return;
        }
        
        user.setNickname(newNickname);
        
        usersRepository.updateUser(user);
        
        resultHandler.onChangeCredentialsSuccess(user);
    }
    
    @Override
    public void changePassword(User user, String oldPassword, String newPassword,
                               ChangeCredentialsResultHandler resultHandler) {
        if (!CredentialsValidationUtils.isValidPassword(newPassword) ||
                !user.getPasswordHash().equals(passwordsService.getPasswordHash(oldPassword))) {
            resultHandler.onChangeCredentialsFailed(ChangeCredentialsErrorCode.INCORRECT_CREDENTIALS);
            return;
        }
        
        user.setPasswordHash(passwordsService.getPasswordHash(newPassword));
        
        usersRepository.updateUser(user);
        
        resultHandler.onChangeCredentialsSuccess(user);
    }
}
