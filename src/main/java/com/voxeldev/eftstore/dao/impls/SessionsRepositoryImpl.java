package com.voxeldev.eftstore.dao.impls;

import com.voxeldev.eftstore.dao.interfaces.SessionsRepository;
import com.voxeldev.eftstore.models.Session;
import com.voxeldev.eftstore.utils.SqlUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.Optional;

public class SessionsRepositoryImpl implements SessionsRepository {
    
    private static final String SQL_INSERT_SESSION = "INSERT INTO sessions (user_id, session_key) VALUES (?, ?)";
    private static final String SQL_SELECT_SESSION_BY_USER_ID = "SELECT * FROM sessions WHERE user_id = ?";
    private static final String SQL_SELECT_SESSION_BY_SESSION_KEY = "SELECT * FROM sessions WHERE session_key = ?";
    private static final String SQL_UPDATE_SESSION_BY_USER_ID = "UPDATE sessions SET session_key = ? WHERE user_id = ?";
    private static final String SQL_DELETE_SESSION_BY_USER_ID = "DELETE FROM sessions WHERE user_id = ?";
    private static final String SQL_DELETE_SESSION_BY_SESSION_KEY = "DELETE FROM sessions WHERE session_key = ?";
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Session> sessionRowMapper = (resultSet, i) -> new Session(
            resultSet.getInt("user_id"),
            resultSet.getString("session_key")
    );
    
    public SessionsRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public void insertSession(Session session) {
        jdbcTemplate.update(SQL_INSERT_SESSION, session.getUserId(), session.getSessionKey());
    }
    
    @Override
    public Optional<Session> getSession(int userId) {
        return SqlUtils.getSingleResult(jdbcTemplate.query(SQL_SELECT_SESSION_BY_USER_ID, sessionRowMapper, userId));
    }
    
    @Override
    public Optional<Session> getSession(String sessionKey) {
        return SqlUtils.getSingleResult(jdbcTemplate.query(SQL_SELECT_SESSION_BY_SESSION_KEY, sessionRowMapper, sessionKey));
    }
    
    @Override
    public void updateSession(Session session) {
        jdbcTemplate.update(SQL_UPDATE_SESSION_BY_USER_ID, session.getSessionKey(), session.getUserId());
    }
    
    @Override
    public void deleteSession(int userId) {
        jdbcTemplate.update(SQL_DELETE_SESSION_BY_USER_ID, userId);
    }
    
    @Override
    public void deleteSession(String sessionKey) {
        jdbcTemplate.update(SQL_DELETE_SESSION_BY_SESSION_KEY, sessionKey);
    }
}
