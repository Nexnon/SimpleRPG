package ru.vsu.cs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vsu.cs.items.Armor;
import ru.vsu.cs.items.Item;
import ru.vsu.cs.items.Weapon;

public class Equipment {

    @JsonProperty("weapon")
    private Weapon weapon;

    @JsonProperty("armor")
    private Armor armor;

    public Equipment() {
        this.weapon = null;
        this.armor = null;
    }

    @JsonCreator
    public Equipment(@JsonProperty("weapon") Weapon weapon, @JsonProperty("armor") Armor armor) {
        this.weapon = weapon;
        this.armor = armor;
    }

    public Item getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Item getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "weapon=" + weapon +
                ", armor=" + armor +
                '}';
    }
}
