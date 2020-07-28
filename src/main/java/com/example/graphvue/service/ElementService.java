package com.example.graphvue.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.graphvue.dao.ElementDAO;
import com.example.graphvue.pojo.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementService {
    @Autowired
    ElementDAO elementDAO;

    public String findAll() {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        List<Element> list = elementDAO.findAll();
        for (Element user:list) {
            array.add(user);
        }
        jsonObject.put("elements",array);
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }
    public String setElement(int id, String name){
        elementDAO.updateName(id, name);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }
}