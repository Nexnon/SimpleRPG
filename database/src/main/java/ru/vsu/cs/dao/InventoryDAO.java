package ru.vsu.cs.dao;

import ru.vsu.cs.*;
import ru.vsu.cs.dao.items.ArmorDAO;
import ru.vsu.cs.dao.items.ItemDAO;
import ru.vsu.cs.dao.items.WeaponDAO;
import ru.vsu.cs.items.Armor;
import ru.vsu.cs.items.Item;
import ru.vsu.cs.items.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO extends AbstractDAO{
    public InventoryDAO(Connection connection) {
        super(connection);
    }

    public void createInventory(Player player){
        List<InventoryCell> inventoryCells = player.getInventory().getInventory();
        String sql = "INSERT INTO inventory (player_id, row_position, column_position, item_id) VALUES (?,?,?,?)";

        for(InventoryCell cell: inventoryCells){
            try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
                statement.setInt(1, player.getId());
                statement.setInt(2, cell.getRow());
                statement.setInt(3, cell.getCol());
                statement.setInt(4, cell.getItem().getId());
                statement.executeUpdate();
                System.out.println("Добавление успешно!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public Inventory readInventoryByPlayerId(int player_id){
        String sql = "SELECT * FROM inventory WHERE player_id = ?";
        ItemDAO itemDAO = new ItemDAO(getConnection());
        ArmorDAO armorDAO = new ArmorDAO(getConnection());
        WeaponDAO weaponDAO = new WeaponDAO(getConnection());
        SkillDAO skillDAO = new SkillDAO(getConnection());
        List<InventoryCell> inventoryCells = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, player_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int row = resultSet.getInt("row_position");
                int col = resultSet.getInt("col_position");
                int item_id = resultSet.getInt("item_id");
                Armor armor = armorDAO.readArmorById(item_id);
                Weapon weapon = weaponDAO.readWeaponById(item_id);
                inventoryCells.add(new InventoryCell(row, col, (armor == null ? weapon : armor)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Inventory(inventoryCells);
    }
    public void updateInventoryByPlayerId(Player player){
        String sql = "UPDATE inventory SET item_id = ? WHERE row_position = ? AND column_position = ? AND player_id = ?";
        List<InventoryCell> inventoryCells = player.getInventory().getInventory();
        for(InventoryCell inventoryCell: inventoryCells){
            try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
                statement.setInt(1, inventoryCell.getItem().getId());
                statement.setInt(2, inventoryCell.getRow());
                statement.setInt(3, inventoryCell.getCol());
                statement.setInt(4, player.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void deleteInventoryByPlayerId(Player player){
        String sql = "DELETE FROM inventory WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, player.getId());
            statement.executeUpdate();
            System.out.println("Currency удален!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Inventory> getAll() throws SQLException {
        return null;
    }
}
