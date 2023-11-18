package ru.vsu.cs.dao.items;

import ru.vsu.cs.dao.AbstractDAO;
import ru.vsu.cs.items.Armor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArmorDAO extends AbstractDAO {
    public ArmorDAO(Connection connection) {
        super(connection);
    }

    public void createArmor(String name, int defence){
        ItemDAO itemDAO = new ItemDAO(getConnection());
        itemDAO.createItem(name);
        int item_id = itemDAO.readItemByName(name);
        String sql = "INSERT INTO armor (defense, item_id) VALUES (?,?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, defence);
            statement.setInt(2, item_id);
            statement.executeUpdate();
            System.out.println("Добавление успешно!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createArmor(Armor armor){
        ItemDAO itemDAO = new ItemDAO(getConnection());
        itemDAO.createItem(armor.getName());
        int item_id = itemDAO.readItemByName(armor.getName());
        String sql = "INSERT INTO armor (defense, item_id) VALUES (?,?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, armor.getArmor());
            statement.setInt(2, item_id);
            statement.executeUpdate();
            System.out.println("Добавление успешно!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Armor readArmorByName(String name){
        String sql = "SELECT * FROM armor WHERE item_id = ?";
        ItemDAO itemDAO = new ItemDAO(getConnection());
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            int item_id = itemDAO.readItemByName(name);
            statement.setInt(1, item_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Armor armor = new Armor(resultSet.getInt("item_id"), name, resultSet.getInt("defense"));
                    armor.setArmor_id(resultSet.getInt("armor_id"));
                    return armor;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Armor readArmorById(int id){
        String sql = "SELECT * FROM armor WHERE armor_id = ?";
        ItemDAO itemDAO = new ItemDAO(getConnection());
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = itemDAO.readItemById(resultSet.getInt("item_id"));
                    Armor armor = new Armor(resultSet.getInt("item_id"), name, resultSet.getInt("defense"));
                    armor.setArmor_id(id);
                    System.out.println(name);
                    return armor;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateArmor(int id, int defense){
        String sql = "UPDATE armor SET defense = ? WHERE armor_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, defense);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteArmor(int id){
        String sql = "DELETE FROM armor WHERE armor_id = ?";
        int item_id = readArmorById(id).getId();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Armor удален!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ItemDAO itemDAO = new ItemDAO(getConnection());
        itemDAO.deleteItem(item_id);
    }

    @Override
    public List<Armor> getAll() throws SQLException {
        String sql = "SELECT * FROM armor";
        ItemDAO itemDAO = new ItemDAO(getConnection());
        List<Armor> armors = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = itemDAO.readItemById(resultSet.getInt("item_id"));
                Armor armor = new Armor(resultSet.getInt("item_id"), name, resultSet.getInt("defense"));
                armor.setArmor_id(resultSet.getInt("armor_id"));
                armors.add(armor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return armors;
    }
}
