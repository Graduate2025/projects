package com.example.dashboard.Model;
//this class help us to build structure to singel task and we will use it in SQl
public class toDoModel {
    private int id, status;
    private String task, user;

    //Id help us to access specific task
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getUser(){return user;}

    public void setUser(String user){this.user = user;}
}

