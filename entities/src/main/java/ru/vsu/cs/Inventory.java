package ru.vsu.cs;

import ru.vsu.cs.items.Item;

public class Inventory {
    private final Item[][] matrix;

    public Inventory() {
        this.matrix = new Item[10][6];
    }

    public Item getItem(int row, int col) {
        return matrix[row][col];
    }

    public void setItem(int row, int col, Item item) {
        this.matrix[row][col] = item;
    }
}
