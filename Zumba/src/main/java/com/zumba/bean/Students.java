package com.zumba.bean;

public class Students {
    private int studentId;
    private String name;
    private String telephone;
    private String email;
    private int batchId; // Batch Foreign Key
    private String batchType; // Extra field to store batch details
    private String batchTime;

    // Default Constructor
    public Students() {}

    // Constructor matching the StudentsController call
    public Students(int studentId, String name, String telephone, String email, int batchId) {
        this.studentId = studentId;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.batchId = batchId;
    }

    // Constructor for search results (including batch details)
    public Students(int studentId, String name, String telephone, String email, int batchId, String batchType, String batchTime) {
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

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
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
