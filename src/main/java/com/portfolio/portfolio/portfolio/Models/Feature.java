package com.portfolio.portfolio.portfolio.Models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="feature")
@EntityListeners(AuditingEntityListener.class)
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer featureID;

    @Column(name = "feature_description", nullable = false)
    private String featureDescription;

    @Column(name = "feature_projectID")
    private long projectID;

    public Feature() {
    }

    public Feature(String featureDescription, long projectID) {
        this.featureDescription = featureDescription;
        this.projectID = projectID;
    }

    public Integer getFeatureID() {
        return featureID;
    }

    public void setFeatureID(Integer featureID) {
        this.featureID = featureID;
    }

    public String getFeatureDescription() {
        return featureDescription;
    }

    public void setFeatureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
    }

    public long getProjectID() {
        return projectID;
    }

    public void setProjectID(long projectID) {
        this.projectID = projectID;
    }
}
