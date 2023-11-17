package ru.vsu.cs.dao;

import ru.vsu.cs.SkillTree;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SkillTreeDAO extends AbstractDAO{
    public SkillTreeDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<SkillTree> getAll() throws SQLException {
        return null;
    }
}
