package com.example.graphvue.dao;

import com.example.graphvue.pojo.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface ProjectDAO extends JpaRepository<Project, Integer> {
    Project findProjectById(Integer id);
    List<Project> findAll();

    @Transactional
    @Modifying
    @Query("update Project e set e.name = ?2 where e.id = ?1")
    void updateName(int id,String name);
}
