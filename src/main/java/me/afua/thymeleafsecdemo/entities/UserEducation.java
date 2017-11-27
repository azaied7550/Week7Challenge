package me.afua.thymeleafsecdemo.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class UserEducation {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String institution;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @NotEmpty
    private String degree;

    @NotEmpty
    private String major;

    @NotNull
    private int educationStart;

    private int educationEnd;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getEducationStart() {
        return educationStart;
    }

    public void setEducationStart(int educationStart) {
        this.educationStart = educationStart;
    }

    public int getEducationEnd() {
        return educationEnd;
    }

    public void setEducationEnd(int educationEnd) {
        this.educationEnd = educationEnd;
    }
}
