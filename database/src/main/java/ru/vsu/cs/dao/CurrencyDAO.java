package ru.vsu.cs.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Currency;
import java.util.List;

public class CurrencyDAO extends AbstractDAO{
    public CurrencyDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Currency> getAll() throws SQLException {
        return null;
    }
}
