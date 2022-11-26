package com.voxeldev.eftstore.services.impls;

import com.voxeldev.eftstore.dao.interfaces.UsersRepository;
import com.voxeldev.eftstore.models.Session;
import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.models.UserAuthentication;
import com.voxeldev.eftstore.models.enums.AuthenticationErrorCode;
import com.voxeldev.eftstore.services.interfaces.AuthenticationService;
import com.voxeldev.eftstore.services.interfaces.SessionsService;
import com.voxeldev.eftstore.utils.ServletUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class AuthenticationServiceImpl implements AuthenticationService {
    
    private final UsersRepository usersRepository;
    private final SessionsService sessionsService;
    private final PasswordsServiceImpl passwordsService;
    
    public AuthenticationServiceImpl(
            UsersRepository usersRepository, SessionsService sessionsService, PasswordsServiceImpl passwordsService) {
        this.usersRepository = usersRepository;
        this.sessionsService = sessionsService;
        this.passwordsService = passwordsService;
    }
    
    @Override
    public void authenticateUser(
            HttpServletRequest request, HttpServletResponse response,
            UserAuthentication userAuthentication, AuthResultHandler authResultHandler
    ) {
        Optional<User> user = usersRepository.getUser(userAuthentication.getEmail());
        
        if (user.isPresent()) {
            if (passwordsService.checkPassword(userAuthentication.getPassword(), user.get().getPasswordHash())) {
                setSessionCookie(response, user.get());
                ServletUtils.setSessionUser(request, user.get());
                authResultHandler.onAuthSuccess(user.get());
            } else {
                authResultHandler.onAuthFailed(AuthenticationErrorCode.INCORRECT_CREDENTIALS);
            }
        } else {
            authResultHandler.onAuthFailed(AuthenticationErrorCode.USER_NOT_FOUND);
        }
    }
    
    @Override
    public boolean authenticateUser(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        
        if (cookies == null) {
            return false;
        }
        
        Cookie sessionKeyCookie = Arrays
                .stream(cookies)
                .filter(cookie -> cookie.getName().equals(SessionsServiceImpl.SESSION_COOKIE_NAME))
                .findFirst()
                .orElse(null);
        
        if (sessionKeyCookie == null) {
            return false;
        }
        
        String sessionKey = sessionKeyCookie.getValue();
        
        Optional<Session> session = sessionsService.getSession(sessionKey);
        
        if (!session.isPresent()) {
            return false;
        }
        
        Optional<User> user = usersRepository.getUser(session.get().getUserId());
        
        user.ifPresent(value -> ServletUtils.setSessionUser(request, value));
        
        return user.isPresent();
    }
    
    private void setSessionCookie(HttpServletResponse response, User user) {
        String sessionKey = UUID.randomUUID().toString();
        sessionsService.createSession(new Session(user.getId(), sessionKey));
        
        Cookie cookie = new Cookie(SessionsServiceImpl.SESSION_COOKIE_NAME, sessionKey);
        cookie.setPath("/");
        
        response.addCookie(cookie);
    }
}
