package com.workintech.s17d2.model;

import com.workintech.s17d2.enums.Experience;
import org.springframework.stereotype.Component;

public class Developer {
    private Integer id;
    private String name;
    private Double salary;
    private Experience experience;

    public Developer() {
    }

    public Developer(Integer id, String name, Double salary, Experience experience) {
        this(id,name,salary);
        this.experience = experience;
    }

    public Developer(Integer id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }
}
