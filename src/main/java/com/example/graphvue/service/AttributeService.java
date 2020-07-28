package com.example.graphvue.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.graphvue.dao.AttributeDAO;
import com.example.graphvue.pojo.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}