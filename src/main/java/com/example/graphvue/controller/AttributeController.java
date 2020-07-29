package com.example.graphvue.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.graphvue.dao.AttributeDAO;
import com.example.graphvue.pojo.Attribute;
import com.example.graphvue.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
public class AttributeController {

    @Autowired
    AttributeService attributeService;

    @CrossOrigin
    @PostMapping(value = "/graph/getAttributes")
    @ResponseBody
    public String getAttributes(@RequestBody JSONObject json) {
        return attributeService.findAllByEid(json.getInteger("eid"));
    }

}