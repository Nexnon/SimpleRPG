package ru.vsu.cs.dao;

import ru.vsu.cs.Inventory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class InventoryDAO extends AbstractDAO{
    public InventoryDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Inventory> getAll() throws SQLException {
        return null;
    }
}
