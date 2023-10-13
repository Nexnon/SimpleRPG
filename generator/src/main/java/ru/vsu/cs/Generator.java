package ru.vsu.cs;

import ru.vsu.cs.items.Armor;
import ru.vsu.cs.items.Item;
import ru.vsu.cs.items.Weapon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    private static final List<String> NICKNAMES = readFile("nicknames.txt");

    public static List<Player> generate(int count){
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

    private static void init(Player player){
        setRandomInventory(player);
        setRandomBalance(player);
        setRandomEquipment(player);
        setRandomSkillTree(player);
    }

    private static void setRandomInventory(Player player){
        Random random = new Random();
        double cItem = 0.15;
        double cType = 0.5;

        int rows = 10;
        int cols = 6;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(random.nextDouble(1) < cItem){
                    player.getInventory().setItem(i, j, (random.nextDouble(1) < cType) ? new Armor(1, "Chestplate", random.nextInt(10) + 3) : new Weapon(0, "Sword", random.nextInt(50) + 20));
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

    private static void setRandomEquipment(Player player){
        Random random = new Random();
        Weapon weapon = new Weapon(0, "Sword", random.nextInt(50) + 20);
        Armor armor = new Armor(1, "Chestplate", random.nextInt(10) + 3);

        player.getEquipment().setArmor(armor);
        player.getEquipment().setWeapon(weapon);
    }

    private static void setRandomSkillTree(Player player){
        Random random = new Random();
        for (int i = 0; i < 3; i++){
            player.getSkillTree().getSkill(i).setResearched(random.nextBoolean());
        }
    }

}
