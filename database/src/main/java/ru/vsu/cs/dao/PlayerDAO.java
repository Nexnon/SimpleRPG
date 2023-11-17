package ru.vsu.cs.dao;

import ru.vsu.cs.Player;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlayerDAO extends AbstractDAO{
    public PlayerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Player> getAll() throws SQLException {
        return null;
    }
}
