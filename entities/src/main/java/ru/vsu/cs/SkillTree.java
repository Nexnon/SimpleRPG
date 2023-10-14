package ru.vsu.cs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class SkillTree {
    private final List<Skill> skills;

    @JsonIgnore
    private final static String[] NAMES = {"Bonus Damage", "Bonus bounty", "Bonus armor"};

    public SkillTree() {
        this.skills = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            skills.add(new Skill(i, NAMES[i]));
        }
    }


    @JsonCreator
    public SkillTree(List<Skill> skillList) {
        this.skills = skillList;
    }

    public Skill getSkill(int id) {
        return skills.get(id);
    }

    public void setSkill(int id, boolean isResearched) {
        skills.get(id).setResearched(isResearched);
    }

    @Override
    public String toString() {
        return "SkillTree{" +
                "skillList=" + skills +
                '}';
    }
}
