package com.example.graphvue.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "attribute_table")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Attribute {

    @Id
    @Column(name = "attribute_id")
    int id;
    @Column(name = "element_id")
    int eid;
    @Column(name = "attribute_name")
    String name;
    @Column(name = "attribute_default_value")
    String value;
    @Column(name = "attribute_unit")
    String unit;


    public void setId(int id) {
        this.id = id;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public int getEid() {
        return eid;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

}

