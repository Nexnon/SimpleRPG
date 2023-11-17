package ru.vsu.cs.dao;

import ru.vsu.cs.InventoryCell;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class InventoryCellDAO extends AbstractDAO{
    public InventoryCellDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<InventoryCell> getAll() throws SQLException {
        return null;
    }
}
