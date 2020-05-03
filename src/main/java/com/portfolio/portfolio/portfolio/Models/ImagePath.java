package com.portfolio.portfolio.portfolio.Models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(name="image")
@EntityListeners(AuditingEntityListener.class)
public class ImagePath {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long imagePathID;

    @Column(name = "imagepath_path", nullable = false)
    private String path;

    @Column(name = "imagepath_projectID")
    private long projectID;



    public ImagePath() {
    }

    public ImagePath(long imagePathID, String path, long projectID) {
        this.imagePathID = imagePathID;
        this.path = path;
        this.projectID = projectID;
    }



    public long getImagePathID() {
        return imagePathID;
    }

    public void setImagePathID(long imagePathID) {
        this.imagePathID = imagePathID;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public long getProjectID() {
        return projectID;
    }

    public void setProjectID(long projectID) {
        this.projectID = projectID;
    }

}


