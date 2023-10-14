package ru.vsu.cs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vsu.cs.items.Item;

import java.util.*;

public class Inventory {
    private final List<InventoryCell> inventory;

    public Inventory() {
        inventory = new ArrayList<>();
    }
    @JsonIgnore
    public Item getItem(int row, int col) {
        for (InventoryCell inventoryCell : inventory) {
            if (inventoryCell.equals(new InventoryCell(row, col, null))) {
                return inventoryCell.getItem();
            }
        }
        return null;
    }

    public void setItem(int row, int col, Item item) {
        for (InventoryCell inventoryCell : inventory) {
            if (inventoryCell.equals(new InventoryCell(row, col, item))) {
                inventoryCell.setItem(item);
                return;
            }
        }
        this.inventory.add(new InventoryCell(row, col, item));
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "matrix=" + inventory +
                '}';
    }
}
