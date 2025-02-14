package com.zumba.bean;

public class Students {
    private int studentId;
    private String name;
    private String telephone;
    private String email;
    private Integer batchId;
    private String batchType; // New field
    private String batchTime; // New field

    // Constructor for inserting a new student (without batch details)
    public Students(String name, String telephone, String email, Integer batchId) {
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.batchId = batchId;
    }

    // Constructor for retrieving a student with full details, including batch info
    public Students(int studentId, String name, String telephone, String email, Integer batchId, String batchType, String batchTime) {
        this.studentId = studentId;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.batchId = batchId;
        this.batchType = batchType;
        this.batchTime = batchTime;
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getBatchType() {
        return batchType;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    public String getBatchTime() {
        return batchTime;
    }

    public void setBatchTime(String batchTime) {
        this.batchTime = batchTime;
    }
}
