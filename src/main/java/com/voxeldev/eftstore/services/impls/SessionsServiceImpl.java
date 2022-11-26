package com.voxeldev.eftstore.services.impls;

import com.voxeldev.eftstore.dao.interfaces.SessionsRepository;
import com.voxeldev.eftstore.models.Session;
import com.voxeldev.eftstore.services.interfaces.SessionsService;

import java.util.Optional;

public class SessionsServiceImpl implements SessionsService {
    
    public static final String SESSION_COOKIE_NAME = "skey";
    
    private final SessionsRepository sessionsRepository;
    
    public SessionsServiceImpl(SessionsRepository sessionsRepository) {
        this.sessionsRepository = sessionsRepository;
    }
    
    @Override
    public boolean isExists(String sessionKey) {
        return getSession(sessionKey).isPresent();
    }
    
    @Override
    public void createSession(Session session) {
        if (isExists(session.getUserId())) {
            sessionsRepository.updateSession(session);
        } else {
            sessionsRepository.insertSession(session);
        }
    }
    
    private boolean isExists(int userId) {
        return getSession(userId).isPresent();
    }
    
    @Override
    public Optional<Session> getSession(int userId) {
        return sessionsRepository.getSession(userId);
    }
    
    @Override
    public Optional<Session> getSession(String sessionKey) {
        return sessionsRepository.getSession(sessionKey);
    }
    
    @Override
    public void updateSession(Session session) {
        sessionsRepository.updateSession(session);
    }
    
    @Override
    public void deleteSession(int userId) {
        sessionsRepository.deleteSession(userId);
    }
    
    @Override
    public void deleteSession(String sessionKey) {
        sessionsRepository.deleteSession(sessionKey);
    }
}
