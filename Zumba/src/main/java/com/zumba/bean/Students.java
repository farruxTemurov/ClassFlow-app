package com.zumba.bean;

public class Students {
    private int studentId;
    private String name;
    private String telephone;
    private String email;
    private int batchId;
    private String batchType; // Add this
    private String batchTime; // Add this

    // ✅ Constructor for retrieving students without batch details
    public Students(int studentId, String name, String telephone, String email, int batchId) {
        this.studentId = studentId;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.batchId = batchId;
    }

    // ✅ Constructor for retrieving students with batch details
    public Students(int studentId, String name, String telephone, String email, int batchId, String batchType, String batchTime) {
        this.studentId = studentId;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.batchId = batchId;
        this.batchType = batchType;
        this.batchTime = batchTime;
    }

    // ✅ Getters
    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public int getBatchId() {
        return batchId;
    }

    public String getBatchType() { // ✅ Add getter for batchType
        return batchType;
    }

    public String getBatchTime() { // ✅ Add getter for batchTime
        return batchTime;
    }
}
