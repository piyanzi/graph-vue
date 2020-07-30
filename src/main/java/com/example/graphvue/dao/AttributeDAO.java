package com.example.graphvue.dao;

import com.example.graphvue.pojo.Attribute;
import com.example.graphvue.pojo.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AttributeDAO extends JpaRepository<Attribute,Integer> {
    List<Attribute> findAllByEid(Integer eid);
    List<Attribute> findAll();
    Attribute findById(int id);

    @Transactional
    @Modifying
    @Query("update Attribute e set e.eid = ?2,e.name = ?3,e.value = ?4 where e.id = ?1")
    void updateAttribute(int id,int eid,String name,String value);

}