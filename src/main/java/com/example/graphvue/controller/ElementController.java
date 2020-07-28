package com.example.graphvue.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.graphvue.pojo.Element;
import com.example.graphvue.pojo.User;
import com.example.graphvue.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ElementController {

    @Autowired
    ElementService elementService;

    @CrossOrigin
    @PostMapping(value = "/graph/getElements")
    @ResponseBody
    public String getElement() {
        return elementService.findAll();
    }

    @CrossOrigin
    @PostMapping(value = "/graph/setElements")
    @ResponseBody
    public String setElement(@RequestBody Element element){
        return elementService.setElement(element.getId(),element.getName());
    }
}