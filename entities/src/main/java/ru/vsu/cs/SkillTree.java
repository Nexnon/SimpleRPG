package ru.vsu.cs;

import java.util.ArrayList;
import java.util.List;

public class SkillTree {
    private final List<Skill> skillList;
    private final static String[] NAMES = {"Bonus Damage", "Bonus bounty", "Bonus armor"};

    public SkillTree() {
        this.skillList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            skillList.add(new Skill(i, NAMES[i]));
        }
    }

    public Skill getSkill(int id) {
        return skillList.get(id);
    }

    public void setSkill(int id, boolean researched) {
        skillList.get(id).setResearched(researched);
    }
}
