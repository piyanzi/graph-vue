package com.example.graphvue.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

@Controller
public class ProjectController {

    //模型保存读取test
    @PostMapping(value = "/graph/getTestModel")
    @ResponseBody
    public String getModel()  {
        StringBuilder s = new StringBuilder();
        try{
            File file = new File("src/main/resources/models/111/1.xml");
            InputStreamReader in = new InputStreamReader(new FileInputStream(file),"UTF-8");
            BufferedReader br = new BufferedReader(in);
            String line = "";
            while ((line=br.readLine())!=null){
                s = s.append(line);
            }
        }catch (Exception e) {
            System.out.println(e);
            return "error";
        }
        return s.toString();
    }

    @PostMapping(value = "/graph/saveTestModel")
    @ResponseBody
    public String setModel(@RequestBody JSONObject file)  {
        try{
            File filepath = new File("src/main/resources/models/111/1.xml");
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filepath,false),"UTF-8");
            BufferedWriter br = new BufferedWriter(out);
            String str = file.get("file").toString();
            br.write(str);
            br.flush();
            br.close();
        }catch (Exception e) {
            System.out.println(e);
            JSONObject json = new JSONObject();
            json.put("code",1);
            return json.toJSONString();
        }
        JSONObject json = new JSONObject();
        json.put("code",0);
        return json.toJSONString();
    }
}
