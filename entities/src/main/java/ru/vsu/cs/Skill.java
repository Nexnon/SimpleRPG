package ru.vsu.cs;

public class Skill {
    private final int id;
    private boolean isResearched;
    private final String name;

    public Skill(int id, String name) {
        this.id = id;
        this.isResearched = false;
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

    public void setResearched(boolean researched) {
        isResearched = researched;
    }
}
