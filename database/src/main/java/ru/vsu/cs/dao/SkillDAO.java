package ru.vsu.cs.dao;

import ru.vsu.cs.Skill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SkillDAO extends AbstractDAO{
    public SkillDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Skill> getAll() throws SQLException {
        return null;
    }
}
