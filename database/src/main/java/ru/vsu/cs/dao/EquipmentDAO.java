package ru.vsu.cs.dao;

import ru.vsu.cs.Equipment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EquipmentDAO extends AbstractDAO{
    public EquipmentDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Equipment> getAll() throws SQLException {
        return null;
    }
}
