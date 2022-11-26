package com.voxeldev.eftstore.dao.impls;

import com.voxeldev.eftstore.dao.interfaces.UsersRepository;
import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.utils.SqlUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {
    
    private static final String SQL_INSERT_USER = "INSERT INTO users (email, password_hash, nickname, avatar_id) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SQL_SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SQL_SELECT_USER_BY_NICKNAME = "SELECT * FROM users WHERE nickname = ?";
    private static final String SQL_UPDATE_USER = "UPDATE users SET email = ?, password_hash = ?, nickname = ?, avatar_id = ? WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userRowMapper = (row, rowNumber) -> new User(
            row.getInt("id"),
            row.getString("email"),
            row.getString("password_hash"),
            row.getString("nickname"),
            row.getObject("avatar_id", Integer.class)
    );
    
    public UsersRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public void insertUser(User user) {
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(SQL_INSERT_USER);
                    preparedStatement.setString(1, user.getEmail());
                    preparedStatement.setString(2, user.getPasswordHash());
                    preparedStatement.setString(3, user.getNickname());
                    if (user.getAvatarId().isPresent())
                        preparedStatement.setInt(4, user.getAvatarId().get());
                    else
                        preparedStatement.setNull(4, Types.NULL);
                    
                    return preparedStatement;
                }
        
        );
    }
    
    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, userRowMapper);
    }
    
    @Override
    public Optional<User> getUser(int id) {
        return SqlUtils.getSingleResult(jdbcTemplate.query(SQL_SELECT_USER_BY_ID, userRowMapper, id));
    }
    
    @Override
    public Optional<User> getUser(String email) {
        return SqlUtils.getSingleResult(jdbcTemplate.query(SQL_SELECT_USER_BY_EMAIL, userRowMapper, email));
    }
    
    @Override
    public Optional<User> getUserByNickname(String nickname) {
        return SqlUtils.getSingleResult(jdbcTemplate.query(SQL_SELECT_USER_BY_NICKNAME, userRowMapper, nickname));
    }
    
    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(
                SQL_UPDATE_USER,
                user.getEmail(),
                user.getPasswordHash(),
                user.getNickname(),
                user.getAvatarId().orElse(null),
                user.getId()
        );
    }
}
