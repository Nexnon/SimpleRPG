package ru.vsu.cs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Balance {
    private final List<Currency> currencies;

    public Balance(){
        this.currencies = new ArrayList<>();
        currencies.add(new Currency(0, 0, "Silver"));
        currencies.add(new Currency(1, 0, "Gold"));
    }
    @JsonCreator
    public Balance(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    @JsonIgnore
    public int getSilver() {
        return currencies.get(0).getValue();
    }

    public void addSilver(int delta) {
        this.currencies.get(0).addValue(delta);
    }
    @JsonIgnore
    public int getGold() {
        return currencies.get(1).getValue();
    }

    public void addGold(int delta) {
        this.currencies.get(1).addValue(delta);
    }
}
