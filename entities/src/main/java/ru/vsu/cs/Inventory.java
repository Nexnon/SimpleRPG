package ru.vsu.cs;

import ru.vsu.cs.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<List<Item>> matrix;

    public Inventory() {
        matrix = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            matrix.add(new ArrayList<>());
            for(int j = 0; j < 6; j++){
                matrix.get(i).add(null);
            }
        }
    }

    public Item getItem(int row, int col) {
        return matrix.get(row).get(col);
    }

    public void setItem(int row, int col, Item item) {
        this.matrix.get(row).set(col, item);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "matrix=" + matrix +
                '}';
    }
}
