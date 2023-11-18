package ru.vsu.cs;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vsu.cs.items.Item;

import java.util.Objects;

public class InventoryCell {

    @JsonProperty("row")
    private int row;
    @JsonProperty("col")
    private int col;

    @JsonProperty("item")
    private Item item;

    public InventoryCell( @JsonProperty("row") int row, @JsonProperty("col") int col, @JsonProperty("item") Item item) {
        this.row = row;
        this.col = col;
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryCell position = (InventoryCell) o;
        return row == position.row && col == position.col;
    }

    @Override
    public String toString() {
        return "InventoryCell{" +
                "row=" + row +
                ", col=" + col +
                ", item=" + item +
                '}';
    }
}
