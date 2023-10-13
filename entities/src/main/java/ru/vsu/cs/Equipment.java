package ru.vsu.cs;

import ru.vsu.cs.items.Armor;
import ru.vsu.cs.items.Item;
import ru.vsu.cs.items.Weapon;

public class Equipment {
    private Weapon weapon;
    private Armor armor;

    public Equipment() {
        this.weapon = null;
        this.armor = null;
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
}
