package ru.vsu.cs;

import ru.vsu.cs.items.Armor;
import ru.vsu.cs.items.Item;
import ru.vsu.cs.items.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemGenerator {
    public static List<Weapon> generateWeapons(){
        Random random = new Random();
        List<Weapon> items = new ArrayList<>();
        items.add(new Weapon(1, "Sword", random.nextInt(50) + 20));
        items.add(new Weapon(3, "Longsword", random.nextInt(50) + 20));
        items.add(new Weapon(5, "Bow and Arrow", random.nextInt(50) + 20));
        items.add(new Weapon(7, "Dagger", random.nextInt(50) + 20));
        return items;
    }

    public static List<Armor> generateArmors(){
        Random random = new Random();
        List<Armor> items = new ArrayList<>();
        items.add(new Armor(0, "Chestplate", random.nextInt(10) + 3));
        items.add(new Armor(2, "Plate Armor", random.nextInt(10) + 3));
        items.add(new Armor(4, "Chainmail", random.nextInt(10) + 3));
        items.add(new Armor(6, "Leather Armor", random.nextInt(10) + 3));
        return items;
    }

    public static List<Skill> generateSkills(){
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill(0, "Shadowstep Mastery"));
        skills.add(new Skill(1, "Celestial Warding"));
        skills.add(new Skill(2, "Pyrokinetic Surge"));
        skills.add(new Skill(3, "Astral Insight"));
        skills.add(new Skill(4, "Thunderstrike Prowess"));
        return skills;
    }
}
