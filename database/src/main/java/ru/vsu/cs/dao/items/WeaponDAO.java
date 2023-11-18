package ru.vsu.cs.dao.items;

import ru.vsu.cs.dao.AbstractDAO;
import ru.vsu.cs.items.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeaponDAO extends AbstractDAO {
    public WeaponDAO(Connection connection) {
        super(connection);
    }

    public void createWeapon(String name, int damage){
        ItemDAO itemDAO = new ItemDAO(getConnection());
        itemDAO.createItem(name);
        int item_id = itemDAO.readItemByName(name);
        String sql = "INSERT INTO weapon (damage, item_id) VALUES (?,?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, damage);
            statement.setInt(2, item_id);
            statement.executeUpdate();
            System.out.println("Добавление успешно!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createWeapon(Weapon weapon){
        ItemDAO itemDAO = new ItemDAO(getConnection());
        itemDAO.createItem(weapon.getName());
        int item_id = itemDAO.readItemByName(weapon.getName());
        String sql = "INSERT INTO weapon (damage, item_id) VALUES (?,?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, weapon.getDamage());
            statement.setInt(2, item_id);
            statement.executeUpdate();
            System.out.println("Добавление успешно!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Weapon readWeaponByName(String name){
        String sql = "SELECT * FROM weapon WHERE item_id = ?";
        ItemDAO itemDAO = new ItemDAO(getConnection());
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            int item_id = itemDAO.readItemByName(name);
            statement.setInt(1, item_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Weapon weapon = new Weapon(resultSet.getInt("item_id"), name, resultSet.getInt("damage"));
                    weapon.setWeapon_id(resultSet.getInt("weapon_id"));
                    return weapon;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Weapon readWeaponById(int id){
        String sql = "SELECT * FROM weapon WHERE weapon_id = ?";
        ItemDAO itemDAO = new ItemDAO(getConnection());
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = itemDAO.readItemById(resultSet.getInt("item_id"));
                    Weapon weapon = new Weapon(resultSet.getInt("item_id"), name, resultSet.getInt("damage"));
                    weapon.setWeapon_id(id);
                    return weapon;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateWeapon(int id, int damage){
        String sql = "UPDATE weapon SET damage = ? WHERE weapon_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, damage);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteWeapon(int id) {
        String sql = "DELETE FROM weapon WHERE weapon_id = ?";
        int item_id = readWeaponById(id).getId();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Weapon удален!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ItemDAO itemDAO = new ItemDAO(getConnection());
        itemDAO.deleteItem(item_id);
    }
    @Override
    public List<Weapon> getAll() throws SQLException {
        String sql = "SELECT * FROM weapon";
        ItemDAO itemDAO = new ItemDAO(getConnection());
        List<Weapon> weapons = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = itemDAO.readItemById(resultSet.getInt("item_id"));
                Weapon weapon = new Weapon(resultSet.getInt("item_id"), name, resultSet.getInt("damage"));
                weapon.setWeapon_id(resultSet.getInt("weapon_id"));
                weapons.add(weapon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weapons;
    }
}
