package com.workintech.s17d2.model;

import com.workintech.s17d2.enums.Experience;
import org.springframework.stereotype.Component;

public class SeniorDeveloper extends Developer {
    public SeniorDeveloper(Integer id, String name, Double salary) {
        super(id, name, salary);
        setExperience(Experience.SENIOR);
    }
}
