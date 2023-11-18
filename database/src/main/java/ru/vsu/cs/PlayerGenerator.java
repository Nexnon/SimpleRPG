package ru.vsu.cs;

import ru.vsu.cs.items.Armor;
import ru.vsu.cs.items.Weapon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerGenerator {
    private static final List<String> NICKNAMES = readFile("nicknames.txt");

    private static CRUD crud;

    static {
        try {
            crud = new CRUD(DbConnection.connector());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Player> generate(int count) throws SQLException {
        Random random = new Random();
        List<Player> players = new ArrayList<>();
        for(int i = 0; i < count; i++){
            players.add(new Player(NICKNAMES.get(random.nextInt(NICKNAMES.size()))));
            init(players.get(i));
        }
        return players;
    }
    private static List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "Windows-1251");
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static void init(Player player) throws SQLException {
        setRandomInventory(player);
        setRandomBalance(player);
        setRandomEquipment(player);
        setRandomSkillTree(player);
    }

    private static void setRandomInventory(Player player) throws SQLException {
        Random random = new Random();

        List<Weapon> weapons = crud.getAllWeapon();
        List<Armor> armors = crud.getAllArmor();

        double cItem = 0.5;
        double cType = 0.5;

        int rows = 10;
        int cols = 6;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(random.nextDouble(1) < cItem){
                    if(random.nextBoolean()){
                        player.getInventory().setItem(i, j, weapons.get(random.nextInt(weapons.size())));
                    } else {
                        player.getInventory().setItem(i, j, armors.get(random.nextInt(armors.size())));
                    }
                }
            }
        }
    }

    private static void setRandomBalance(Player player){
        Random random = new Random();
        int silver = random.nextInt(1_000_000);
        int gold = random.nextInt(1_000);

        player.getBalance().addSilver(silver);
        player.getBalance().addGold(gold);
    }

    private static void setRandomEquipment(Player player) throws SQLException {

        Random random = new Random();

        List<Weapon> weapons = crud.getAllWeapon();
        List<Armor> armors = crud.getAllArmor();

        Weapon weapon = weapons.get(random.nextInt(weapons.size()));
        Armor armor = armors.get(random.nextInt(armors.size()));

        player.getEquipment().setArmor(armor);
        player.getEquipment().setWeapon(weapon);
    }

    private static void setRandomSkillTree(Player player) throws SQLException {
        Random random = new Random();
        List<Skill> skills = crud.getAllSkills();
        for (int i = 0; i < 3; i++){
            player.setSkillTree(new SkillTree(skills));
        }
    }
}
