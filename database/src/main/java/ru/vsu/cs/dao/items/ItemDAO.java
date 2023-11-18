package ru.vsu.cs.dao.items;

import ru.vsu.cs.Player;
import ru.vsu.cs.dao.AbstractDAO;
import ru.vsu.cs.items.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemDAO extends AbstractDAO {
    public ItemDAO(Connection connection) {
        super(connection);
    }

    public void createItem(String name){
        String sql = "INSERT INTO item (name) VALUES (?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println("Добавление успешно!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int readItemByName(String name){
        String sql = "SELECT * FROM item WHERE name = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("item_id");
                } else {
                    return -1;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String readItemById(int id){
        String sql = "SELECT * FROM item WHERE item_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                } else {
                    return "Error";
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateItem(int item_id, String name){
        String sql = "UPDATE item SET name = ? WHERE item_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, item_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteItem(int id){
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
    public List<Item> getAll() throws SQLException {
        return null;
    }
}
