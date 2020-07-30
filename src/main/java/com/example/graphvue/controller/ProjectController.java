package com.example.graphvue.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.graphvue.pojo.Element;
import com.example.graphvue.pojo.Project;
import com.example.graphvue.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;

    static String path = "src/main/resources";

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

    @PostMapping(value = "/graph/getModel")
    @ResponseBody
    public String getModelById(@RequestBody JSONObject json)  {
        StringBuilder s = new StringBuilder();
        String filepath = projectService.findProjectById(json.getInteger("id"));
        try{
            File file = new File(path + filepath);
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


    @PostMapping(value = "/graph/saveModel")
    @ResponseBody
    public String setModelById(@RequestBody JSONObject jsonObject)  {
        String filepath = projectService.findProjectById(jsonObject.getInteger("id"));
        try{
            File file = new File(path + filepath);
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file,false),"UTF-8");
            BufferedWriter br = new BufferedWriter(out);
            String str = jsonObject.get("file").toString();
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


    @PostMapping(value = "/graph/getProjects")
    @ResponseBody
    public String getProjects() {
        return projectService.findAll();
    }

    @CrossOrigin
    @PostMapping(value = "/graph/setProjects")
    @ResponseBody
    public String setProject(@RequestBody Project project){
        return projectService.setProject(project.getId(),project.getName());
    }

    @CrossOrigin
    @PostMapping(value = "/graph/addProjects")
    @ResponseBody
    public String addProject(@RequestBody Project project){
        try{
            File file = new File(path + "/models/" + project.getUid() + "/" + project.getName() + ".xml");
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file,false),"UTF-8");
            BufferedWriter br = new BufferedWriter(out);
            String str = project.getPath();
            if(str.equals("")) {
                str = "<mxGraphModel><root><mxCell id=\"0\"/><mxCell id=\"1\" parent=\"0\"/></root></mxGraphModel>";
            }
            br.write(str);
            br.flush();
            br.close();
            project.setPath("/models/" + project.getUid() + "/" + project.getName() + ".xml");
        }catch (Exception e) {
            System.out.println(e);
            JSONObject json = new JSONObject();
            json.put("code",1);
            return json.toJSONString();
        }
        return projectService.addProject(project);
    }

    @CrossOrigin
    @PostMapping(value = "/graph/deleteProjects")
    @ResponseBody
    public String deleteProject(@RequestBody Project project){
        return projectService.deleteProject(project.getId());
    }
}
