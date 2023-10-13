package ru.vsu.cs.items;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Armor extends Item{

    @JsonProperty("armor")
    private int armor;
    public Armor(@JsonProperty("id")int id, @JsonProperty("name") String name, @JsonProperty("armor") int armor) {
        super(id, name);
        this.armor = armor;
    }

    @Override
    public String toString() {
        return "Armor{" +
                "armor=" + armor +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}
