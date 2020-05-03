package com.portfolio.portfolio.portfolio.Models;

import com.fasterxml.jackson.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="technology")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Technology implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long technologyID;

    @Column(name = "technology_name", nullable = false)
    private String technologyName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "technologyList")
    private Set<Project> projectList = new HashSet<>();


    public Technology() {
    }

    public Technology(long technologyID, String technologyName, Set<Project> projectList) {
        this.technologyID = technologyID;
        this.technologyName = technologyName;
        this.projectList = projectList;
    }

    public long getTechnologyID() {
        return technologyID;
    }

    public void setTechnologyID(long technologyID) {
        this.technologyID = technologyID;
    }

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }

    public Set<Project> getProjectID() {
        return projectList;
    }

    public void setProjectID(Set<Project> projectID) {
        this.projectList = projectID;
    }
}


