package com.example.graphvue.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "project_table")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    int id;
    @Column(name = "user_id")
    int uid;
    @Column(name = "project_name")
    String name;
    @Column(name = "project_file")
    String path;

    public int getId() {
        return id;
    }

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }



}


