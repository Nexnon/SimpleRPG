package ru.vsu.cs.dao;

import ru.vsu.cs.Equipment;
import ru.vsu.cs.Player;
import ru.vsu.cs.dao.items.ArmorDAO;
import ru.vsu.cs.dao.items.ItemDAO;
import ru.vsu.cs.dao.items.WeaponDAO;
import ru.vsu.cs.items.Armor;
import ru.vsu.cs.items.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EquipmentDAO extends AbstractDAO{
    public EquipmentDAO(Connection connection) {
        super(connection);
    }

    public void createEquipment(Player player){
        String sql = "INSERT INTO equipment (player_id, armor_id, weapon_id) VALUES (?,?,?)";
        Armor armor = player.getEquipment().getArmor();
        Weapon weapon = player.getEquipment().getWeapon();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, player.getId());
            statement.setInt(2, (armor != null) ? armor.getArmor_id(): 10);
            statement.setInt(3, (weapon != null) ? weapon.getWeapon_id(): 7);
            statement.executeUpdate();
            System.out.println("Добавление успешно!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Equipment readEquipmentByPlayerId(int id){
        String sql = "SELECT * FROM equipment WHERE player_id = ?";
        WeaponDAO weaponDAO = new WeaponDAO(getConnection());
        ArmorDAO armorDAO = new ArmorDAO(getConnection());
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Armor armor = armorDAO.readArmorById(resultSet.getInt("armor_id"));
                    Weapon weapon = weaponDAO.readWeaponById(resultSet.getInt("weapon_id"));
                    return new Equipment(weapon, armor);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateEquipment(Player player){
        String sql = "UPDATE equipment SET weapon_id = ?, armor_id = ? WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, player.getEquipment().getWeapon().getId());
            statement.setInt(2, player.getEquipment().getArmor().getId());
            statement.setInt(3, player.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteEquipmentByPlayerId(int id){
        String sql = "DELETE FROM equipment WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Equipment удален!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Equipment> getAll() throws SQLException {
        return null;
    }
}
