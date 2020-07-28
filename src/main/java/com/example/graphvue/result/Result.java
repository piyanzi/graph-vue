package com.example.graphvue.result;

public class Result {
    //响应码
    private int code;
    private int role;

    public Result(int role, int code) {
        this.role = role;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}


