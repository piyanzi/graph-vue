package com.example.graphvue.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.graphvue.dao.ConnectionDAO;
import com.example.graphvue.pojo.Connection;
import com.example.graphvue.pojo.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionService {
    @Autowired
    ConnectionDAO connectionDAO;
    public String findAllByEid(Integer eid) {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        List<Connection> list = connectionDAO.findAllByEid(eid);
        for (Connection Conn:list) {
            array.add(Conn);
        }
        jsonObject.put("connections",array);
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }

    public String findAll() {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        List<Connection> list = connectionDAO.findAll();
        for (Connection user:list) {
            array.add(user);
        }
        jsonObject.put("connections",array);
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }

    public String getConnections(int id,int eid,float cx,float cy){
        connectionDAO.updateConnection(id,eid,cx,cy);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }

    public String deleteConnections(int id){
        connectionDAO.deleteById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }

    public String addConnections(Connection connections){
        connectionDAO.save(connections);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }
}