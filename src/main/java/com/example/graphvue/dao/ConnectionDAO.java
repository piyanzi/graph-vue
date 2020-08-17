package com.example.graphvue.dao;

import com.example.graphvue.pojo.Connection;
import com.example.graphvue.pojo.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ConnectionDAO extends JpaRepository<Connection,Integer>{
    List<Connection> findAllByEid(Integer eid);
    List<Connection> findAll();
    Connection findById(int id);

    @Transactional
    @Modifying
    @Query("update Connection c set c.eid = ?2,c.cx = ?3,c.cy = ?4 where c.id = ?1")
    void updateConnection(int id,int eid,float cx,float cy);

}