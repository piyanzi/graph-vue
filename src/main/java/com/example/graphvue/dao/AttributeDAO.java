package com.example.graphvue.dao;

import com.example.graphvue.pojo.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface AttributeDAO extends JpaRepository<Attribute,Integer> {
    List<Attribute> findAllByEid(Integer eid);


}