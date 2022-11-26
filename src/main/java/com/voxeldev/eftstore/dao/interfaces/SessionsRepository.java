package com.voxeldev.eftstore.dao.interfaces;

import com.voxeldev.eftstore.models.Session;

import java.util.Optional;

public interface SessionsRepository {
    void insertSession(Session session);
    
    Optional<Session> getSession(int userId);
    
    Optional<Session> getSession(String sessionKey);
    
    void updateSession(Session session);
    
    void deleteSession(int userId);
    
    void deleteSession(String sessionKey);
}
