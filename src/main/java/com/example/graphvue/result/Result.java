package com.example.graphvue.result;

public class Result {
    //响应码
    private int code;
    private int role;
    private int uid;

    public Result(int role, int code, int uid) {
        this.role = role;
        this.code = code;
        this.uid = uid;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}


