package com.example.graphvue.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "connection_table")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Connection {

    @Id
    @Column(name = "connection_id")
    int id;
    @Column(name = "element_id")
    int eid;
    @Column(name = "connection_x")
    float cx;
    @Column(name = "connection_y")
    float cy;

    public void setId(int id) {
        this.id = id;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public void setCx(float cx) {
        this.cx = cx;
    }

    public void setCy(float cy) {
        this.cy = cy;
    }

    public int getId() {
        return id;
    }

    public int getEid() {
        return eid;
    }

    public float getcx() {
        return cx;
    }

    public float getcy() {
        return cy;
    }

}

