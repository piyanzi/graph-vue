package com.example.graphvue.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.graphvue.dao.ConnectionDAO;
import com.example.graphvue.pojo.Connection;
import com.example.graphvue.pojo.Element;
import com.example.graphvue.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConnectionController {
    @Autowired
    ConnectionService connectionService;

    @CrossOrigin
    @PostMapping(value = "/graph/getConnections")
    @ResponseBody
    public String getConnections(@RequestBody JSONObject json) {
        return connectionService.findAllByEid(json.getInteger("eid"));
    }

    @CrossOrigin
    @PostMapping(value = "/graph/getAllConnections")
    @ResponseBody
    public String getAllConnections() {
        return connectionService.findAll();
    }

    @CrossOrigin
    @PostMapping(value = "/graph/setConnections")
    @ResponseBody
    public String setConnections(@RequestBody Connection connection) {
        return connectionService.getConnections(connection.getId(),connection.getEid(),connection.getcx(),connection.getcy());
    }

    @CrossOrigin
    @PostMapping(value = "/graph/deleteConnections")
    @ResponseBody
    public String deleteConnections(@RequestBody Connection connection){
        return connectionService.deleteConnections(connection.getId());
    }

    @CrossOrigin
    @PostMapping(value = "/graph/addConnections")
    @ResponseBody
    public String addElement(@RequestBody Connection connection){
        return connectionService.addConnections(connection);
    }
}
