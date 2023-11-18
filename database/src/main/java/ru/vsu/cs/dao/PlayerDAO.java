package ru.vsu.cs.dao;

import ru.vsu.cs.Balance;
import ru.vsu.cs.Equipment;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayerGenerator;
import ru.vsu.cs.dao.items.ItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO extends AbstractDAO{
    public PlayerDAO(Connection connection) {
        super(connection);
    }

    public void createPlayer(String name){
        String sql = "INSERT INTO player (name) VALUES (?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println("Добавление успешно!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createPlayer(Player player){
        String sql = "INSERT INTO player (name) VALUES (?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, player.getName());
            statement.executeUpdate();
            System.out.println("Добавление успешно!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Player pl = readPlayerByName(player.getName());
        player.setId(pl.getId());
        InventoryDAO inventoryDAO = new InventoryDAO(getConnection());
        inventoryDAO.createInventory(player);
        BalanceDAO balanceDAO = new BalanceDAO(getConnection());
        balanceDAO.createBalance(player);
        EquipmentDAO equipmentDAO = new EquipmentDAO(getConnection());
        equipmentDAO.createEquipment(player);
        SkillTreeDAO skillTreeDAO = new SkillTreeDAO(getConnection());
        skillTreeDAO.createSkillTree(player);
    }

    public Player readPlayerByName(String name){
        String sql = "SELECT * FROM player WHERE name = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Player player = new Player(resultSet.getString("name"));
                    player.setId(resultSet.getInt("player_id"));
                    return player;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Player readPlayerById(int id){
        String sql = "SELECT * FROM player WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Player player = new Player(resultSet.getString("name"));
                    return player;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePlayer(int id, String name){
        String sql = "UPDATE player SET name = ? WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePlayer(int id){
        String sql = "DELETE FROM player WHERE player_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Player удален!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Player> getAll() throws SQLException {
        String sql = "SELECT * FROM player";
        List<Player> players = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Player player = new Player();
                player.setId(resultSet.getInt("player_id"));
                player.setName(resultSet.getString("name"));
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
}
