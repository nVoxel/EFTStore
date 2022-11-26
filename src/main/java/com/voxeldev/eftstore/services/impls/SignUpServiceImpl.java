package com.voxeldev.eftstore.services.impls;

import com.voxeldev.eftstore.dao.interfaces.UsersRepository;
import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.models.UserSignUp;
import com.voxeldev.eftstore.models.enums.SignUpErrorCode;
import com.voxeldev.eftstore.services.interfaces.SignUpService;
import com.voxeldev.eftstore.services.interfaces.UsersService;

import static com.voxeldev.eftstore.utils.CredentialsValidationUtils.*;

public class SignUpServiceImpl implements SignUpService {
    
    private final UsersRepository usersRepository;
    private final UsersService usersService;
    
    public SignUpServiceImpl(
            UsersRepository usersRepository, UsersService usersService) {
        this.usersRepository = usersRepository;
        this.usersService = usersService;
    }
    
    @Override
    public void signUpUser(UserSignUp user, SignUpResultHandler signUpResultHandler) {
        if (!isValidEmail(user.getEmail())) {
            signUpResultHandler.onSignUpFailed(SignUpErrorCode.INCORRECT_CREDENTIALS);
            return;
        }
        
        if (!isValidNickname(user.getNickname())) {
            signUpResultHandler.onSignUpFailed(SignUpErrorCode.INCORRECT_CREDENTIALS);
            return;
        }
        
        if (!isValidPassword(user.getPassword())) {
            signUpResultHandler.onSignUpFailed(SignUpErrorCode.INCORRECT_CREDENTIALS);
            return;
        }
        
        if (usersRepository.getUser(user.getEmail()).isPresent()) {
            signUpResultHandler.onSignUpFailed(SignUpErrorCode.EMAIL_ALREADY_REGISTERED);
            return;
        }
        
        if (usersRepository.getUserByNickname(user.getNickname()).isPresent()) {
            signUpResultHandler.onSignUpFailed(SignUpErrorCode.NICKNAME_ALREADY_REGISTERED);
            return;
        }
        
        usersService.saveUser(user);
        
        User registeredUser = usersRepository.getUser(user.getEmail()).get();
        
        signUpResultHandler.onSignUpSuccess(registeredUser);
    }
}
