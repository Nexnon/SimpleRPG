package ru.vsu.cs.dao;

import ru.vsu.cs.Balance;
import ru.vsu.cs.Currency;
import ru.vsu.cs.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BalanceDAO extends AbstractDAO{
    public BalanceDAO(Connection connection) {
        super(connection);
    }

    public void createBalance(Player player){
        List<Currency> currencies = player.getBalance().getCurrencies();
        String sql = "INSERT INTO balance (player_id, name, value) VALUES (?,?,?)";
        for(Currency c: currencies){
            try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
                statement.setInt(1, player.getId());
                statement.setString(2, c.getName());
                statement.setInt(3, c.getValue());
                statement.executeUpdate();
                System.out.println("Добавление успешно!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public Balance readBalanceByPlayerId(int id){
        String sql = "SELECT * FROM balance WHERE player_id = ?";
        List<Currency> currencies = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Currency currency = new Currency(resultSet.getInt("balance_id"), resultSet.getInt("value"), resultSet.getString("name"));
                currencies.add(currency);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Balance(currencies);
    }
    public void updateBalance(Balance balance){
        for (Currency c: balance.getCurrencies()){
            updateCurrency(c);
        }
    }

    public void updateCurrency(Currency currency){
        String sql = "UPDATE balance SET value = ? WHERE balance_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, currency.getValue());
            statement.setInt(2, currency.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteBalance(Balance balance){
        for (Currency c: balance.getCurrencies()){
            deleteCurrency(c.getId());
        }
    }

    public void deleteCurrency(int id){
        String sql = "DELETE FROM balance WHERE balance_id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Currency удален!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Balance> getAll() throws SQLException {
        return null;
    }
}
