package ru.vsu.cs.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Weapon extends Item{

    private int weapon_id;

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
                "weapon_id=" + weapon_id +
                ", damage=" + damage +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getWeapon_id() {
        return weapon_id;
    }

    public void setWeapon_id(int weapon_id) {
        this.weapon_id = weapon_id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
