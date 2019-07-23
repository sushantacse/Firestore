package com.cbiu.firebasefirestore;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Student implements Serializable {

    @Exclude private String id;
    private String name;
    private String phone;

    public Student(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
