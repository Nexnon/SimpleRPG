package ru.vsu.cs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class SkillTree {
    private final List<Skill> skillList;

    @JsonIgnore
    private final static String[] NAMES = {"Bonus Damage", "Bonus bounty", "Bonus armor"};

    public SkillTree() {
        this.skillList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            skillList.add(new Skill(i, NAMES[i]));
        }
    }


    @JsonCreator
    public SkillTree(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public Skill getSkill(int id) {
        return skillList.get(id);
    }

    public void setSkill(int id, boolean isResearched) {
        skillList.get(id).setResearched(isResearched);
    }

    @Override
    public String toString() {
        return "SkillTree{" +
                "skillList=" + skillList +
                '}';
    }
}
