package ru.vsu.cs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private static int lastId = 0;
    @JsonProperty("inventory")
    private Inventory inventory;

    @JsonProperty("equipment")
    private Equipment equipment;

    @JsonProperty("balance")
    private Balance balance;
    @JsonProperty("skillTree")
    private SkillTree skillTree;

    public Player() {

    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", inventory=" + inventory +
                ", equipment=" + equipment +
                ", balance=" + balance +
                ", skillTree=" + skillTree +
                '}';
    }

    public Player(String name) {
        this.id = lastId++;
        this.name = name;
        this.inventory = new Inventory();
        this.equipment = new Equipment();
        this.balance = new Balance();
        this.skillTree = new SkillTree();
    }
    @JsonCreator
    public Player(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("inventory") Inventory inventory,
                  @JsonProperty("equipment") Equipment equipment, @JsonProperty("balance") Balance balance, @JsonProperty("skillTree") SkillTree skillTree) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
        this.equipment = equipment;
        this.balance = balance;
        this.skillTree = skillTree;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public Balance getBalance() {
        return balance;
    }

    public SkillTree getSkillTree() {
        return skillTree;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void setLastId(int lastId) {
        Player.lastId = lastId;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public void setSkillTree(SkillTree skillTree) {
        this.skillTree = skillTree;
    }
}
