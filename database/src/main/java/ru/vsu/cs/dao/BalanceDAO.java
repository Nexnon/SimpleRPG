package ru.vsu.cs.dao;

import ru.vsu.cs.Balance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BalanceDAO extends AbstractDAO{
    public BalanceDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Balance> getAll() throws SQLException {
        return null;
    }
}
