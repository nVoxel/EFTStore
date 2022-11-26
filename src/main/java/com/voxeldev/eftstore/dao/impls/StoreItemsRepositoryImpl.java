package com.voxeldev.eftstore.dao.impls;

import com.voxeldev.eftstore.dao.interfaces.StoreItemsRepository;
import com.voxeldev.eftstore.models.StoreItem;
import com.voxeldev.eftstore.utils.SqlUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StoreItemsRepositoryImpl implements StoreItemsRepository {
    
    private static final String SQL_INSERT_ITEM = "INSERT INTO items (name_ru, name_en, image_name, image_name_det, description_ru, description_en, wiki_link, price_rub) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ITEM = "SELECT * FROM items WHERE id = ?";
    private static final String SQL_SELECT_MULTIPLE_ITEMS = "SELECT * FROM items WHERE id IN (?)";
    private static final String SQL_SELECT_ALL_ITEMS = "SELECT * FROM items";
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<StoreItem> storeItemRowMapper = (resultSet, i) -> new StoreItem(
            resultSet.getInt("id"),
            resultSet.getString("name_ru"),
            resultSet.getString("name_en"),
            resultSet.getString("image_name"),
            resultSet.getString("image_name_det"),
            resultSet.getString("description_ru"),
            resultSet.getString("description_en"),
            resultSet.getString("wiki_link"),
            resultSet.getInt("price_rub")
    );
    
    public StoreItemsRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public void insertItem(StoreItem item) {
        jdbcTemplate.update(
                SQL_INSERT_ITEM,
                item.getNameRu(),
                item.getNameEn(),
                item.getImageName(),
                item.getImageNameDet(),
                item.getDescriptionRu(),
                item.getDescriptionEn(),
                item.getWikiLink(),
                item.getPriceRub()
        );
    }
    
    @Override
    public Optional<StoreItem> getItem(int id) {
        return SqlUtils.getSingleResult(jdbcTemplate.query(SQL_SELECT_ITEM, storeItemRowMapper, id));
    }
    
    @Override
    public List<StoreItem> getMultipleItems(List<Integer> ids) {
        // don't use PreparedStatement here, because it doesn't support arrays;
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        
        return jdbcTemplate.query(
                SQL_SELECT_MULTIPLE_ITEMS.replace(
                        "?", ids.stream().map(String::valueOf).collect(Collectors.joining(","))
                ),
                storeItemRowMapper
        );
    }
    
    @Override
    public List<StoreItem> getAllItems() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS, storeItemRowMapper);
    }
}
