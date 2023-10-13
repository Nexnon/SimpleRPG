package ru.vsu.cs;

public class Player {
    private final Inventory inventory;
    private final Equipment equipment;
    private final Balance balance;
    private final SkillTree skillTree;

    public Player() {
        this.inventory = new Inventory();
        this.equipment = new Equipment();
        this.balance = new Balance();
        this.skillTree = new SkillTree();
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
}
