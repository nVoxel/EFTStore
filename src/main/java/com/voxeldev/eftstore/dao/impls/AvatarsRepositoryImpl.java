package com.voxeldev.eftstore.dao.impls;

import com.voxeldev.eftstore.dao.interfaces.AvatarsRepository;
import com.voxeldev.eftstore.models.Avatar;
import com.voxeldev.eftstore.utils.SqlUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.Optional;

public class AvatarsRepositoryImpl implements AvatarsRepository {
    
    private static final String SQL_INSERT_AVATAR = "INSERT INTO avatars (file_name, content_type, size, " +
            "original_file_name, uploader_id) VALUES (?, ?, ?, ?, ?) RETURNING id";
    private static final String SQL_SELECT_AVATAR_BY_ID = "SELECT * FROM avatars WHERE id = ?";
    private static final String SQL_UPDATE_AVATAR = "UPDATE avatars SET file_name = ?, content_type = ?, " +
            "size = ?, original_file_name = ?, uploader_id = ? WHERE id = ?";
    private static final String SQL_DELETE_AVATAR = "DELETE FROM avatars WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Avatar> avatarRowMapper = (resultSet, i) -> new Avatar(
            resultSet.getInt("id"),
            resultSet.getString("file_name"),
            resultSet.getString("content_type"),
            resultSet.getInt("size"),
            resultSet.getString("original_file_name"),
            resultSet.getInt("uploader_id")
    );
    
    public AvatarsRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public void insertAvatar(Avatar avatar) {
        
        Integer id = jdbcTemplate.queryForObject(
                SQL_INSERT_AVATAR,
                new Object[]{
                        avatar.getFileName(),
                        avatar.getContentType(),
                        avatar.getSize(),
                        avatar.getOriginalFileName(),
                        avatar.getUploaderId()
                },
                Integer.class
        );
        
        if (id != null) {
            avatar.setId(id);
        }
    }
    
    @Override
    public Optional<Avatar> getAvatar(int id) {
        return SqlUtils.getSingleResult(jdbcTemplate.query(SQL_SELECT_AVATAR_BY_ID, avatarRowMapper, id));
    }
    
    @Override
    public void updateAvatar(Avatar avatar) {
        jdbcTemplate.update(
                SQL_UPDATE_AVATAR, avatar.getFileName(), avatar.getContentType(),
                avatar.getSize(), avatar.getOriginalFileName(), avatar.getUploaderId(), avatar.getId());
    }
    
    @Override
    public void deleteAvatar(int id) {
        jdbcTemplate.update(SQL_DELETE_AVATAR, id);
    }
}
