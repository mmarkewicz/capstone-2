package com.company.inventoryservice.dao;

import com.company.inventoryservice.model.Inventory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InventoryDaoImpl implements InventoryDao {

    private static final String INSERT_INVENTORY_SQL =
            "INSERT INTO inventory(product_id, quantity) VALUES (?, ?)";

    private static final String SELECT_INVENTORY_SQL =
            "SELECT * FROM inventory WHERE inventory_id = ?";

    private static final String SELECT_ALL_INVENTORIES_SQL =
            "SELECT * FROM inventory";

    private static final String UPDATE_INVENTORY_SQL =
            "UPDATE inventory SET product_id = ?, quantity = ? WHERE inventory_id = ?";

    private static final String DELETE_INVENTORY_SQL =
            "DELETE FROM inventory WHERE inventory_id = ?";

    private JdbcTemplate jdbcTemplate;

    public InventoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Inventory addInventory(Inventory inventory) {

        jdbcTemplate.update(INSERT_INVENTORY_SQL,
                inventory.getProductId(),
                inventory.getQuantity()
                );

        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        inventory.setId(id);

        return inventory;
    }

    @Override
    public Inventory getInventoryById(int id) {
        return jdbcTemplate.queryForObject(SELECT_INVENTORY_SQL, this::mapRowToInventory, id);
    }

    @Override
    public List<Inventory> getAllInventories() {
        return jdbcTemplate.query(SELECT_ALL_INVENTORIES_SQL, this::mapRowToInventory);
    }

    @Override
    public void updateInventory(Inventory inventory) {
        jdbcTemplate.update(UPDATE_INVENTORY_SQL,
                inventory.getProductId(),
                inventory.getQuantity(),
                inventory.getId()
                );
    }

    @Override
    public void deleteInventoryById(int id) {
        jdbcTemplate.update(DELETE_INVENTORY_SQL, id);
    }

    private Inventory mapRowToInventory(ResultSet rs, int rowNum) throws SQLException {
        Inventory inventory = new Inventory();
        inventory.setId(rs.getInt("inventory_id"));
        inventory.setProductId(rs.getInt("product_id"));
        inventory.setQuantity(rs.getInt("quantity"));
        return inventory;
    }
}
