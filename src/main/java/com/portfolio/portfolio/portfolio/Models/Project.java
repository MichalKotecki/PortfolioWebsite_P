package com.portfolio.portfolio.portfolio.Models;

import com.fasterxml.jackson.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="project")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Project implements Serializable {

    @Id
    @Column(name="projectID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long projectID;

    @Column(name = "project_title", nullable = false)
    private String title;

    @Column(name = "project_date", nullable = false)
    private String date;

    @Column(name = "project_githubrepositorypath", nullable = false)
    private String gitHubRepositoryPath;

    @Column(name = "project_description", nullable = false)
    private String description;


    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "project_imagepathlist", nullable = false)
    @JoinColumn(name="imagepath_projectID", referencedColumnName = "projectID")
    private List<ImagePath> imagePathList;


    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "project_featurelist", nullable = false)
    @JoinColumn(name="feature_projectID", referencedColumnName = "projectID")
    private List<Feature> featureList;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "Project_Technology",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "technology_id") }
    )
    private Set<Technology> technologyList = new HashSet<>();


    public Project() {
    }

    public Project(long projectID, String title, String date, String gitHubRepositoryPath, String description, List<ImagePath> ImagePathList, List<Feature> featureList, Set<Technology> technologyList) {
        this.projectID = projectID;
        this.title = title;
        this.date = date;
        this.gitHubRepositoryPath = gitHubRepositoryPath;
        this.description = description;
        this.imagePathList = ImagePathList;
      this.technologyList = technologyList;
       this.featureList = featureList;
    }


    public long getProjectID() {
        return projectID;
    }

    public void setProjectID(long projectID) {
        this.projectID = projectID;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getGitHubRepositoryPath() {
        return gitHubRepositoryPath;
    }

    public void setGitHubRepositoryPath(String gitHubRepositoryPath) {
        this.gitHubRepositoryPath = gitHubRepositoryPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ImagePath> getImagePathList() {
        return imagePathList;
    }

    public void setImagePathList(List<ImagePath> imagePathList) {
        this.imagePathList = imagePathList;
    }

    public List<Feature> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public Set<Technology> getTechnologyList() {
        return technologyList;
    }

    public void setTechnologyList(Set<Technology> technologyList) {
        this.technologyList = technologyList;
    }
}
