package com.example.graphvue.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.graphvue.dao.ProjectDAO;
import com.example.graphvue.pojo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectDAO projectDAO;

    public String findProjectById(Integer id) {
        Project project = projectDAO.findProjectById(id);
        return project.getPath();
    }

}