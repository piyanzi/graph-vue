package com.example.graphvue.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.graphvue.dao.ElementDAO;
import com.example.graphvue.pojo.Element;
import com.example.graphvue.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class ElementService {
    @Autowired
    ElementDAO elementDAO;

    static String path = "src/main/resources/static/";

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
    public String addElement(Element element){
        elementDAO.save(element);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }
    public String deleteElement(int id){
        Element element = elementDAO.findById(id);
        File file =new File(path + element.getPath());
        file.delete();
        elementDAO.deleteById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }
}