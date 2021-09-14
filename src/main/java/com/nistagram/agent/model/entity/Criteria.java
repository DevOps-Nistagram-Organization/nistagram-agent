package com.nistagram.agent.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Criteria {
    @GeneratedValue
    @Id
    private Long id;

    @Column
    private String gender;
    @Column
    private Integer minAge;
    @Column
    private Integer maxAge;

    public Criteria() {
    }

    public Criteria(Long id, String gender, Integer minAge, Integer maxAge) {
        this.id = id;
        this.gender = gender;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }
}
