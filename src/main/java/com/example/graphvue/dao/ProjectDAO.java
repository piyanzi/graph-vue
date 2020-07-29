package com.example.graphvue.dao;

import com.example.graphvue.pojo.Project;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProjectDAO extends JpaRepository<Project, Integer> {
    Project findProjectById(Integer id);


}
