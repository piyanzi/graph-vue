package com.example.graphvue.dao;

import com.example.graphvue.pojo.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ElementDAO extends JpaRepository<Element,Integer> {
    List<Element> findAll();


    @Transactional
    @Modifying
    @Query("update Element e set e.name = ?2 where e.id = ?1")
    void updateName(int id,String name);


}