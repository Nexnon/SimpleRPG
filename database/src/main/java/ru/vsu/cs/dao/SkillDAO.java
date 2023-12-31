package ru.vsu.cs.dao;

import ru.vsu.cs.Skill;
import ru.vsu.cs.dao.items.ItemDAO;
import ru.vsu.cs.items.Armor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillDAO extends AbstractDAO{
    public SkillDAO(Connection connection) {
        super(connection);
    }

    public void createSkill(String name){
        String sql = "INSERT INTO skill (name) VALUES (?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println("Добавление успешно!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createSkill(Skill  skill){
        String sql = "INSERT INTO skill (name) VALUES (?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, skill.getName());
            statement.executeUpdate();
            System.out.println("Добавление успешно!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Skill readSkillByName(String name){
        String sql = "SELECT * FROM skill WHERE name = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Skill(resultSet.getInt("skill_id"), name);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Skill readSkillById(int id){
        String sql = "SELECT * FROM skill WHERE skill_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Skill(id, resultSet.getString("name"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateSkill(int item_id, String name){
        String sql = "UPDATE item SET name = ? WHERE item_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, item_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteSkill(int id){
        String sql = "DELETE FROM item WHERE item_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Item удален!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Skill> getAll() throws SQLException {
        String sql = "SELECT * FROM skill";
        List<Skill> skills = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Skill skill = new Skill(resultSet.getInt("skill_id"), resultSet.getString("name"));
                skills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }
}
