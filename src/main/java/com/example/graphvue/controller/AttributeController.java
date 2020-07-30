package com.example.graphvue.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.graphvue.dao.AttributeDAO;
import com.example.graphvue.pojo.Attribute;
import com.example.graphvue.pojo.Element;
import com.example.graphvue.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

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

    @CrossOrigin
    @PostMapping(value = "/graph/getAllAttributes")
    @ResponseBody
    public String getAllAttributes() {
        return attributeService.findAll();
    }

    @CrossOrigin
    @PostMapping(value = "/graph/setAttributes")
    @ResponseBody
    public String setAttributes(@RequestBody Attribute attribute) {
        return attributeService.getAttributes(attribute.getId(),attribute.getEid(),attribute.getName(),attribute.getValue());
    }

    @CrossOrigin
    @PostMapping(value = "/graph/deleteAttributes")
    @ResponseBody
    public String deleteAttributes(@RequestBody Attribute attribute){
        return attributeService.deleteAttributes(attribute.getId());
    }

    @CrossOrigin
    @PostMapping(value = "/graph/addAttributes")
    @ResponseBody
    public String addElement(@RequestBody Attribute attribute){
        return attributeService.addAttributes(attribute);
    }
}