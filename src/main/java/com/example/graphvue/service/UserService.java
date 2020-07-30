package com.example.graphvue.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.graphvue.dao.UserDAO;
import com.example.graphvue.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String username) {
        return userDAO.findByUsername(username);
    }

    public User get(String username, String password){
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public String add(User user) {
        userDAO.save(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }

    public String findAll() {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        List<User> list = userDAO.findAll();
        for (User user: list) {
            array.add(user);
        }
        jsonObject.put("users",array);
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }

    public String deleteUser(int id){
        userDAO.deleteById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }

    public String setUser(int id, String username, String password, int role){
        userDAO.setUser(id, username, password ,role);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }


}
