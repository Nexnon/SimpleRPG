package ru.vsu.cs;

public class Balance {
    private int silver;
    private int gold;

    public Balance(){
        this.silver = 0;
        this.gold = 0;
    }

    public int getSilver() {
        return silver;
    }

    public void addSilver(int delta) {
        this.silver += delta;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int delta) {
        this.gold += delta;
    }
}
