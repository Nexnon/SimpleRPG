package ru.vsu.cs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency {
    private int id;
    private int value;
    private String name;

    public Currency(@JsonProperty("id") int id,@JsonProperty("value") int value,@JsonProperty("name") String name) {
        this.id = id;
        this.value = value;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void addValue(int value) {
        this.value += value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
