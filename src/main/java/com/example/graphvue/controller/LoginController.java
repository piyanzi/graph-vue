package com.example.graphvue.controller;

import com.example.graphvue.pojo.User;
import com.example.graphvue.result.Result;
import com.example.graphvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/loginIn")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        User user = userService.get(username, requestUser.getPassword());
        if (null == user) {
            return new Result(-1,400, 0);
        } else {
            if(user.getRole() == 1) {
                return new Result(1,200, user.getId());
            }
            else {
                return new Result(0,200, user.getId());
            }
        }
    }

    @CrossOrigin
    @PostMapping(value = "/graph/getUsers")
    @ResponseBody
    public String getUsers() {
        return userService.findAll();
    }

    @CrossOrigin
    @PostMapping(value = "/graph/deleteUsers")
    @ResponseBody
    public String deleteUser(@RequestBody User user){
        return userService.deleteUser(user.getId());
    }

    @CrossOrigin
    @PostMapping(value = "/graph/addUsers")
    @ResponseBody
    public String addUser(@RequestBody User user){
        return userService.add(user);
    }

    @CrossOrigin
    @PostMapping(value = "/graph/setUsers")
    @ResponseBody
    public String setUser(@RequestBody User user){
        return userService.setUser(user.getId(),user.getUsername(),user.getPassword(),user.getRole());
    }


}

