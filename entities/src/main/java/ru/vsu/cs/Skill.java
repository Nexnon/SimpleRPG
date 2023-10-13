package ru.vsu.cs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Skill {

    @JsonProperty("id")
    private final int id;

    @JsonProperty("isResearched")
    private boolean isResearched;

    @JsonProperty("name")
    private final String name;

    public Skill(int id, String name) {
        this.id = id;
        this.isResearched = false;
        this.name = name;
    }
    @JsonCreator
    public Skill(@JsonProperty("id") int id, @JsonProperty("isResearched") boolean isResearched, @JsonProperty("name") String name) {
        this.id = id;
        this.isResearched = isResearched;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public boolean isResearched() {
        return isResearched;
    }

    public String getName() {
        return name;
    }

    public void setResearched(boolean isResearched) {
        this.isResearched = isResearched;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", isResearched=" + isResearched +
                ", name='" + name + '\'' +
                '}';
    }
}
