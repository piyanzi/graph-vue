package com.example.graphvue.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.graphvue.dao.ProjectDAO;
import com.example.graphvue.pojo.Element;
import com.example.graphvue.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectDAO projectDAO;

    static String path = "src/main/resources";

    public String findProjectById(Integer id) {
        Project project = projectDAO.findProjectById(id);
        return project.getPath();
    }

    public String findAll() {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        List<Project> list = projectDAO.findAll();
        for (Project p:list) {
            array.add(p);
        }
        jsonObject.put("projects",array);
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }

    public String setProject(int id, String name){
        projectDAO.updateName(id, name);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }

    public String addProject(Project project){
        projectDAO.save(project);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }

    public String deleteProject(int id){
        Project project = projectDAO.findProjectById(id);
        File file =new File(path + project.getPath());
        file.delete();
        projectDAO.deleteById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        return jsonObject.toJSONString();
    }

}