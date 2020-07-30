package com.example.graphvue.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.graphvue.dao.AttributeDAO;
import com.example.graphvue.pojo.Attribute;
import com.example.graphvue.pojo.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class AttributeService {
    @Autowired
    AttributeDAO attributeDAO;

    public String findAllByEid(Integer eid) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        List<Attribute> list = attributeDAO.findAllByEid(eid);
        for (Attribute attr:list) {
            array.add(attr);
        }
        jsonObject.put("attributes",array);
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }
    public String findAll() {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        List<Attribute> list = attributeDAO.findAll();
        for (Attribute user:list) {
            array.add(user);
        }
        jsonObject.put("attributes",array);
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }
    public String getAttributes(int id,int eid, String name,String value){
        attributeDAO.updateAttribute(id,eid,name,value);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }
    public String deleteAttributes(int id){
        attributeDAO.deleteById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }
    public String addAttributes(Attribute attributes){
        attributeDAO.save(attributes);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }
}