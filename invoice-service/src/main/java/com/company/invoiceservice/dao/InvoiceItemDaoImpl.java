package com.company.invoiceservice.dao;

import com.company.invoiceservice.model.InvoiceItem;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InvoiceItemDaoImpl implements InvoiceItemDao {

    private static final String INSERT_INVOICE_ITEM_SQL =
            "INSERT INTO invoice_item(invoice_id, inventory_id, quantity, unit_price) VALUES (?, ?, ?, ?)";

    private static final String SELECT_INVOICE_ITEM_SQL =
            "SELECT * FROM invoice_item WHERE invoice_item_id = ?";

    private static final String SELECT_INVOICE_ITEMS_BY_INVOICE_ID_SQL =
            "SELECT * FROM invoice_item WHERE invoice_id = ?";

    private static final String SELECT_ALL_INVOICE_ITEMS_SQL =
            "SELECT * FROM invoice_item";

    private static final String UPDATE_INVOICE_ITEM_SQL =
            "UPDATE invoice_item SET invoice_id = ?, inventory_id = ?, quantity = ?, unit_price = ? WHERE invoice_item_id = ?";

    private static final String DELETE_INVOICE_SQL =
            "DELETE FROM invoice_item WHERE invoice_item_id = ?";

    private JdbcTemplate jdbcTemplate;

    public InvoiceItemDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(INSERT_INVOICE_ITEM_SQL,
                invoiceItem.getInvoiceId(),
                invoiceItem.getInventory_id(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitPrice()
                );

        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        invoiceItem.setId(id);

        return invoiceItem;
    }

    @Override
    public InvoiceItem getInvoiceItemById(int id) {
        return jdbcTemplate.queryForObject(SELECT_INVOICE_ITEM_SQL, this::mapRowToInvoiceItem, id);
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return jdbcTemplate.query(SELECT_ALL_INVOICE_ITEMS_SQL, this::mapRowToInvoiceItem);
    }

    @Override
    public List<InvoiceItem> getInvoiceItemsByInvoiceId(int invoiceId) {
        return jdbcTemplate.query(SELECT_INVOICE_ITEMS_BY_INVOICE_ID_SQL, this::mapRowToInvoiceItem, invoiceId);
    }

    @Override
    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(INSERT_INVOICE_ITEM_SQL,
                invoiceItem.getInvoiceId(),
                invoiceItem.getInventory_id(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitPrice(),
                invoiceItem.getId()
        );
    }

    @Override
    public void deleteInvoiceItem(int id) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, id);
    }

    private InvoiceItem mapRowToInvoiceItem(ResultSet rs, int rowNum) throws SQLException {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setId(rs.getInt("invoice_item_id"));
        invoiceItem.setInventory_id(rs.getInt("inventory_id"));
        invoiceItem.setInvoiceId(rs.getInt("invoice_id"));
        invoiceItem.setQuantity(rs.getInt("quantity"));
        invoiceItem.setUnitPrice(rs.getBigDecimal("unit_price"));
        return invoiceItem;
    }
}
