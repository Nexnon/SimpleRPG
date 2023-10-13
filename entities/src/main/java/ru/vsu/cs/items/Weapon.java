package ru.vsu.cs.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Weapon extends Item{

    @JsonProperty("damage")
    private int damage;

    @JsonCreator
    public Weapon(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("damage") int damage) {
        super(id, name);
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "damage=" + damage +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
