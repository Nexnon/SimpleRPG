package ru.vsu.cs;

import ru.vsu.cs.items.Armor;
import ru.vsu.cs.items.Weapon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class InitializationDB {
    private static CRUD crud;
    public static void main(String[] args) throws SQLException {
        generateArmors();
        generateSkills();
        generateWeapons();
        generatePlayers();
    }

    public static void generateArmors() throws SQLException {
        crud = new CRUD(DbConnection.connector());
        List<Armor> armors = ItemGenerator.generateArmors();
        for(Armor armor: armors){
            crud.createArmor(armor);
        }
    }
    public static void generateWeapons() throws SQLException {
        crud = new CRUD(DbConnection.connector());
        List<Weapon> weapons = ItemGenerator.generateWeapons();
        for(Weapon weapon: weapons){
            crud.createWeapon(weapon);
        }
    }
    public static void generateSkills() throws SQLException {
        crud = new CRUD(DbConnection.connector());
        List<Skill> skills = ItemGenerator.generateSkills();
        for(Skill skill: skills){
            crud.createSkill(skill);
        }
    }
    public static void generatePlayers() throws SQLException {
        crud = new CRUD(DbConnection.connector());
        List<Player> players = PlayerGenerator.generate(100000);
        for(Player player: players){
            crud.createPlayer(player);
        }
    }
}
