package com.voxeldev.eftstore.services.interfaces;

import com.voxeldev.eftstore.models.Session;

import java.util.Optional;

public interface SessionsService {
    boolean isExists(String sessionKey);
    
    void createSession(Session session);
    
    Optional<Session> getSession(int userId);
    
    Optional<Session> getSession(String sessionKey);
    
    void updateSession(Session session);
    
    void deleteSession(int userId);
    
    void deleteSession(String sessionKey);
}
