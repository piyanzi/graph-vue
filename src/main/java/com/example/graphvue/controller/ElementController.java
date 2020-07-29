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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

@Controller
public class ElementController {
    static String path = "src/main/resources/static/elements/";
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

    @CrossOrigin
    @PostMapping(value = "/graph/addElements")
    @ResponseBody
    public String addElement(@RequestBody Element element){
        try{
            File file = new File(path + element.getName() + ".svg");
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file,false),"UTF-8");
            BufferedWriter br = new BufferedWriter(out);
            String str = element.getPath();
            br.write(str);
            br.flush();
            br.close();
            element.setPath("/elements/" + element.getName() + ".svg");
        }catch (Exception e) {
            System.out.println(e);
            JSONObject json = new JSONObject();
            json.put("code",1);
            return json.toJSONString();
        }
        return elementService.addElement(element);
    }
}