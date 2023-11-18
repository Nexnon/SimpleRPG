package ru.vsu.cs.dao;

import ru.vsu.cs.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillTreeDAO extends AbstractDAO{
    public SkillTreeDAO(Connection connection) {
        super(connection);
    }

    public void createSkillTree(Player player){
        List<Skill> skills = player.getSkillTree().getSkills();
        String sql = "INSERT INTO skilltree (player_id, skill_id, is_researched) VALUES (?,?,?)";
        for(Skill sk: skills){
            try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
                statement.setInt(1, player.getId());
                statement.setInt(2, sk.getId());
                statement.setBoolean(3, sk.isResearched());
                statement.executeUpdate();
                System.out.println("Добавление успешно!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public SkillTree readSkillTree(int id){
        String sql = "SELECT * FROM skilltree WHERE player_id = ?";
        SkillDAO skillDAO = new SkillDAO(getConnection());
        List<Skill> skills = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Skill skill = skillDAO.readSkillById(resultSet.getInt("skill_id"));
                skills.add(skill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new SkillTree(skills);
    }
    public void updateSkillTree(Player player){
        String sql = "UPDATE skilltree SET is_researched = ? WHERE skill_id = ? AND player_id = ?";
        List<Skill> skills = player.getSkillTree().getSkills();
        for(Skill skill: skills){
            try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
                statement.setBoolean(1, skill.isResearched());
                statement.setInt(2, skill.getId());
                statement.setInt(3, player.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void deleteSkillTreeByPlayerId(int id){
        String sql = "DELETE FROM skilltree WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Currency удален!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SkillTree> getAll() throws SQLException {
        return null;
    }
}
