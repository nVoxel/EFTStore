package com.voxeldev.eftstore.dao.impls;

import com.voxeldev.eftstore.dao.interfaces.CartItemsRepository;
import com.voxeldev.eftstore.models.CartItem;
import com.voxeldev.eftstore.models.User;
import com.voxeldev.eftstore.utils.SqlUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CartItemsRepositoryImpl implements CartItemsRepository {
    
    private static final String SQL_INSERT_CART_ITEM = "INSERT INTO cart_items (user_id, item_id) VALUES (?, ?)";
    private static final String SQL_SELECT_CART_ITEMS_BY_USER_ID = "SELECT * FROM cart_items WHERE user_id = ?";
    private static final String SQL_SELECT_CART_ITEM_BY_ID = "SELECT * FROM cart_items WHERE id = ?";
    private static final String SQL_DELETE_CART_ITEM_BY_ID = "DELETE FROM cart_items WHERE id = ?";
    private static final String SQL_DELETE_CART_ITEM_BY_USER_ID = "DELETE FROM cart_items WHERE user_id = ?";
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<CartItem> cartItemRowMapper = (resultSet, i) -> new CartItem(
            resultSet.getInt("id"),
            resultSet.getInt("user_id"),
            resultSet.getInt("item_id")
    );
    
    public CartItemsRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public Optional<CartItem> getCartItem(int id) {
        return SqlUtils.getSingleResult(jdbcTemplate.query(SQL_SELECT_CART_ITEM_BY_ID, cartItemRowMapper, id));
    }
    
    @Override
    public List<CartItem> getUserCartItems(User user) {
        return jdbcTemplate.query(SQL_SELECT_CART_ITEMS_BY_USER_ID, cartItemRowMapper, user.getId());
    }
    
    @Override
    public void insertCartItem(CartItem cartItem) {
        jdbcTemplate.update(SQL_INSERT_CART_ITEM, cartItem.getUserId(), cartItem.getItemId());
    }
    
    @Override
    public void deleteCartItem(CartItem cartItem) {
        jdbcTemplate.update(SQL_DELETE_CART_ITEM_BY_ID, cartItem.getId());
    }
    
    @Override
    public void deleteCartItems(int userId) {
        jdbcTemplate.update(SQL_DELETE_CART_ITEM_BY_USER_ID, userId);
    }
}
